package ru.glindaquint.everwell.dto.requests.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO запроса для аутентификации пользователя в системе. Содержит учетные данные пользователя для
 * входа.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на аутентификацию пользователя")
public class SignInRequest {

  /** Уникальный идентификатор пользователя (логин или email). */
  @Schema(
      description = "Уникальный идентификатор пользователя (логин или email)",
      requiredMode = Schema.RequiredMode.REQUIRED,
      example = "user@example.com",
      minLength = 5,
      maxLength = 50)
  @Size(min = 5, max = 50, message = "Длина идентификатора должна быть от 5 до 50 символов")
  @NotBlank(message = "Идентификатор пользователя не может быть пустым")
  @Pattern(regexp = "^[a-zA-Z0-9_@.-]+$", message = "Допустимы только буквы, цифры и символы _@.-")
  private String username;

  /** Секретный пароль пользователя. */
  @Schema(
      description = "Пароль пользователя",
      requiredMode = Schema.RequiredMode.REQUIRED,
      example = "My$ecretP@ssw0rd",
      minLength = 8,
      maxLength = 255)
  @Size(min = 8, max = 255, message = "Длина пароля должна быть от 8 до 255 символов")
  @NotBlank(message = "Пароль не может быть пустым")
  private String password;
}
