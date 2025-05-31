package ru.glindaquint.everwell.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.glindaquint.everwell.dto.requests.users.UpdateUserRequest;
import ru.glindaquint.everwell.exceptions.auth.BadEmailException;
import ru.glindaquint.everwell.exceptions.auth.BadPasswordException;
import ru.glindaquint.everwell.exceptions.auth.BadUsernameException;
import ru.glindaquint.everwell.models.BadHabit;
import ru.glindaquint.everwell.models.User;
import ru.glindaquint.everwell.repo.UserRepository;
import ru.glindaquint.everwell.types.Sex;

/**
 * Сервис для манипуляции пользователями.
 *
 * @see UserRepository
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    /**
     * Создание пользователя. Метод имеет транзакционный способ доступа к БД
     *
     * @param user Доменная модель пользователя, необязательные поля могут отсутствовать
     * @see User
     */
    @Transactional
    public void create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new BadUsernameException();
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new BadEmailException();
        }

        if (user.getPassword()
                .length() < 5) {
            throw new BadPasswordException();
        }

        repository.save(user);
    }

    /**
     * Получение пользователя по имени.
     *
     * @return Пользователь
     * @throws UsernameNotFoundException Если пользователь по имени не найден
     * @see User
     */
    @Transactional(readOnly = true)
    public User getByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                         .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    /**
     * Получение пользователя по имени пользователя, нужен для Spring Security.
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    /**
     * Получение текущего пользователя.
     *
     * @return текущий пользователь
     */
    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext()
                                            .getAuthentication()
                                            .getName();
        return getByUsername(username);
    }

    public boolean restorePassword(String email, String password) {
        var user = repository.findByEmail(email);
        if (user.isEmpty()) {
            return false;
        }
        user.get()
            .setPassword(password);
        repository.save(user.get());

        return true;
    }

    public void save(User user) {
        repository.save(user);
    }

    @Transactional
    public void updateUser(UpdateUserRequest request) {
        User user = getCurrentUser();

        user.setLastname(request.getLastname());
        user.setFirstname(request.getFirstname());
        user.setPatronymic(request.getPatronymic());
        user.setHeight(request.getHeight());
        user.setWeight(request.getWeight());
        user.setBirthDate(request.getBirthDate());
        user.setSex(Sex.valueOf(request.getSex()));
        user.setDiseases(request.getDiseases());

        // removing unchecked habits
        user.getBadHabits()
            .removeIf(habit -> !request.getBadHabits()
                                       .contains(habit.getTitle()));

        // saving new habits
        request.getBadHabits()
               .forEach(habit -> {
                   if (user.getBadHabits()
                           .stream()
                           .noneMatch(badHabit -> badHabit.getTitle()
                                                          .equals(habit))) {
                       user.getBadHabits()
                           .add(BadHabit.builder()
                                        .title(habit)
                                        .user(user)
                                        .build());
                   }
               });

        save(user);
    }
}
