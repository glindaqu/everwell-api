package ru.glindaquint.everwell.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.glindaquint.everwell.exceptions.auth.BadEmailException;
import ru.glindaquint.everwell.exceptions.auth.BadPasswordException;
import ru.glindaquint.everwell.exceptions.auth.BadUsernameException;
import ru.glindaquint.everwell.models.User;
import ru.glindaquint.everwell.repo.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    /**
     * Создание пользователя
     */
    public void create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new BadUsernameException();
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new BadEmailException();
        }

        if (user.getPassword().length() < 5) {
            throw new BadPasswordException();
        }

        repository.save(user);
    }

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }
}