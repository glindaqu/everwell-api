package ru.glindaquint.everwell.dto.tasks;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.glindaquint.everwell.types.Repeat;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema
public class InsertTaskRequest {
    private String title;

    private String description;

    private Repeat repeat;

    private Boolean isNotificationEnabled;

    private Date deadlineDate;
}
