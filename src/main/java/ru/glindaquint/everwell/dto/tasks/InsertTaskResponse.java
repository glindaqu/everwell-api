package ru.glindaquint.everwell.dto.tasks;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.glindaquint.everwell.models.Task;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema
public class InsertTaskResponse {
    private Task task;
}
