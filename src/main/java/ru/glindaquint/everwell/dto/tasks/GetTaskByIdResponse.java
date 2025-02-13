package ru.glindaquint.everwell.dto.tasks;

import lombok.Data;
import ru.glindaquint.everwell.models.Task;

@Data
public class GetTaskByIdResponse {
    private Task task;
}
