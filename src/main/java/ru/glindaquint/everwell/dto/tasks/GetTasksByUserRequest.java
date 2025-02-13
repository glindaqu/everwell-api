package ru.glindaquint.everwell.dto.tasks;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Запрос на получение всех задач пользователя")
public class GetTasksByUserRequest {

    @Schema(description = "Id пользователя", example = "1")
    private Long userId;
}