package ru.glindaquint.everwell.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.glindaquint.everwell.dto.users.GetUserResponse;
import ru.glindaquint.everwell.dto.users.UpdateProfileRequest;
import ru.glindaquint.everwell.models.User;
import ru.glindaquint.everwell.services.UserService;

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
                .build()
        );
    }

    @PostMapping("/update-profile")
    public ResponseEntity<?> updateProfile(@RequestBody @Valid UpdateProfileRequest request) {

    }
}