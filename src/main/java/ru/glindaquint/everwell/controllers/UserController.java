package ru.glindaquint.everwell.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.glindaquint.everwell.dto.users.GetUserResponse;
import ru.glindaquint.everwell.dto.users.UpdateProfileRequest;
import ru.glindaquint.everwell.models.BadHabit;
import ru.glindaquint.everwell.models.User;
import ru.glindaquint.everwell.services.BadHabitService;
import ru.glindaquint.everwell.services.UserService;
import ru.glindaquint.everwell.types.Sex;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Users")
public class UserController {
    private final UserService userService;
    private final BadHabitService badHabitService;

    @GetMapping("/get-user")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public ResponseEntity<?> getUser() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(GetUserResponse.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .patronymic(user.getPatronymic())
                .sex(String.valueOf(user.getSex()))
                .weight(user.getWeight())
                .height(user.getHeight())
                .diseases(user.getDiseases())
                .birthDate(user.getBirthDate())
                .badHabits(user.getBadHabits()
                        .stream()
                        .map(BadHabit::getTitle)
                        .collect(Collectors.toSet())
                ).build()
        );
    }

    @PostMapping("/update-profile")
    public ResponseEntity<?> updateProfile(@RequestBody @Valid UpdateProfileRequest request) {
        User user = userService.getCurrentUser();

        user.setLastname(request.getLastname());
        user.setFirstname(request.getFirstname());
        user.setPatronymic(request.getPatronymic());
        user.setHeight(request.getHeight());
        user.setWeight(request.getWeight());
        user.setBirthDate(request.getBirthDate());
        user.setSex(Sex.valueOf(request.getSex()));
        user.setDiseases(request.getDiseases());

        // removing unchecked habits
        for (BadHabit habit : user.getBadHabits()) {
            if (!request.getBadHabits().contains(habit.getTitle())) {
                badHabitService.delete(habit);
            }
        }

        for (String habit : request.getBadHabits()) {
            if (!user.getBadHabits()
                    .stream()
                    .map(BadHabit::getTitle)
                    .collect(Collectors.toSet())
                    .contains(habit)
            ) {
                badHabitService.save(
                        BadHabit.builder()
                                .title(habit)
                                .user(user)
                                .build()
                );
            }
        }

        userService.save(user);

        return ResponseEntity.ok("saved");
    }
}