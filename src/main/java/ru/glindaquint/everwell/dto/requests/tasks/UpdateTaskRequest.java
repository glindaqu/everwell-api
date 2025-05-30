package ru.glindaquint.everwell.dto.requests.tasks;

import lombok.*;
import ru.glindaquint.everwell.types.Repeat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTaskRequest {
    private Long taskId;

    private String title;

    private String description;

    private Date creationDate;

    private Date lastChangeDate;

    private Long ownerId;

    private Repeat repeat;

    private Boolean isNotificationEnabled;

    private Date deadlineDate;

    private Boolean isCompleted;
}
