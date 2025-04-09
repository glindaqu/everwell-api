package ru.glindaquint.everwell.dto.responses.tasks;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.glindaquint.everwell.models.Task;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema
public class GetTaskByIdResponse {
    private Task task;
}
