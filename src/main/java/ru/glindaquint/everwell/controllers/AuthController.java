package ru.glindaquint.everwell.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.glindaquint.everwell.dto.requests.auth.RestoreRequest;
import ru.glindaquint.everwell.dto.requests.auth.SignInRequest;
import ru.glindaquint.everwell.dto.requests.auth.SignUpRequest;
import ru.glindaquint.everwell.dto.responses.auth.JwtAuthenticationResponse;
import ru.glindaquint.everwell.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequest request) {
        try {
            return ResponseEntity.ok(new JwtAuthenticationResponse(
                    authenticationService.signUp(request)
            ));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInRequest request) {
        try {
            return ResponseEntity.ok(new JwtAuthenticationResponse(
                    authenticationService.signIn(request)
            ));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/restore-password")
    public ResponseEntity<?> restorePassword(@RequestBody @Valid RestoreRequest request) {
        return ResponseEntity.ok(authenticationService.restorePassword(request));
    }
}