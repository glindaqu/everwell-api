package ru.glindaquint.everwell.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.glindaquint.everwell.dto.users.GetUserResponse;
import ru.glindaquint.everwell.dto.users.UpdateUserRequest;
import ru.glindaquint.everwell.models.BadHabit;
import ru.glindaquint.everwell.models.User;
import ru.glindaquint.everwell.services.UserService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Users")
public class UserController {
    private final UserService userService;

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
    public ResponseEntity<?> updateUser(@RequestBody @Valid UpdateUserRequest request) {
        try {
            userService.updateUser(request);
            return ResponseEntity.ok("saved");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }
    }
}