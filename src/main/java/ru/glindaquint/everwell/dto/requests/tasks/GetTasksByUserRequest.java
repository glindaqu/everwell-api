package ru.glindaquint.everwell.dto.requests.tasks;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на получение всех задач пользователя")
public class GetTasksByUserRequest {

    @Schema(description = "Id пользователя", example = "1")
    private Long userId;
}