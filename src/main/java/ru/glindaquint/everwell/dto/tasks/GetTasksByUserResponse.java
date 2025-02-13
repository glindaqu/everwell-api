package ru.glindaquint.everwell.dto.tasks;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.glindaquint.everwell.models.Task;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ответ с задачами пользователя")
public class GetTasksByUserResponse {

    @Schema(description = "Массив задач пользователя")
    private List<Task> tasks;
}