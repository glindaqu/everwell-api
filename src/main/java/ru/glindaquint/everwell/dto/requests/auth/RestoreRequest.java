package ru.glindaquint.everwell.dto.requests.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO запроса на восстановление доступа к аккаунту.
 * Содержит данные, необходимые для сброса пароля пользователя.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на восстановление доступа к аккаунту")
public class RestoreRequest {

    /**
     * Электронная почта пользователя для восстановления доступа.
     * Должна быть валидным email-адресом.
     */
    @Schema(
            description = "Электронная почта пользователя",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "user@example.com",
            format = "email"
    )
    private String email;

    /**
     * Новый пароль для аккаунта.
     * Должен соответствовать требованиям безопасности системы.
     */
    @Schema(
            description = "Новый пароль пользователя",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "NewSecurePassword123!",
            minLength = 8,
            maxLength = 64
    )
    private String password;
}