package ru.glindaquint.everwell.dto.requests.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для запроса регистрации нового пользователя.
 * Содержит все необходимые поля для создания учетной записи с валидацией.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Запрос на регистрацию нового пользователя в системе")
public class SignUpRequest {

    /**
     * Уникальное имя пользователя для входа в систему.
     * Должно содержать только латинские буквы, цифры и подчеркивание.
     */
    @NotBlank(message = "Имя пользователя обязательно для заполнения")
    @Size(min = 5, max = 50, message = "Длина имени пользователя должна быть от 5 до 50 символов")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$",
            message = "Имя пользователя может содержать только латинские буквы, цифры и подчеркивание")
    @Schema(description = "Уникальное имя пользователя (логин)",
            example = "john_doe123",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 5,
            maxLength = 50)
    private String username;

    /**
     * Электронная почта пользователя.
     * Должна быть в валидном формате email.
     */
    @NotBlank(message = "Email обязателен для заполнения")
    @Email(message = "Некорректный формат email адреса")
    @Size(max = 255, message = "Email не должен превышать 255 символов")
    @Schema(description = "Электронная почта пользователя",
            example = "user@example.com",
            requiredMode = Schema.RequiredMode.REQUIRED,
            format = "email",
            maxLength = 255)
    private String email;

    /**
     * Пароль пользователя.
     * Должен соответствовать требованиям безопасности.
     */
    @NotBlank(message = "Пароль обязателен для заполнения")
    @Size(min = 8, max = 255, message = "Длина пароля должна быть от 8 до 255 символов")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Пароль должен содержать минимум 1 заглавную букву, 1 строчную букву, 1 цифру и 1 спецсимвол")
    @Schema(description = "Пароль пользователя",
            example = "SecurePass123!",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 8,
            maxLength = 255)
    private String password;
}