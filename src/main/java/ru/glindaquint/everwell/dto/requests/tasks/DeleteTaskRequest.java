package ru.glindaquint.everwell.dto.requests.tasks;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.glindaquint.everwell.types.Repeat;

import java.util.Date;

/**
 * DTO (Data Transfer Object) для запроса на удаление задачи.
 * Содержит все необходимые поля, связанные с удаляемой задачей.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "DTO запроса на удаление задачи")
public class DeleteTaskRequest {
    /**
     * Уникальный идентификатор задачи
     */
    @Schema(description = "Уникальный идентификатор задачи", example = "12345")
    private Long taskId;

    /**
     * Название задачи
     */
    @Schema(description = "Название задачи", example = "Провести встречу")
    private String title;

    /**
     * Описание задачи
     */
    @Schema(description = "Подробное описание задачи", example = "Обсудить новый проект с командой")
    private String description;

    /**
     * Дата создания задачи
     */
    @Schema(description = "Дата и время создания задачи", example = "2025-01-15T10:00:00Z")
    private Date creationDate;

    /**
     * Дата последнего изменения задачи
     */
    @Schema(description = "Дата и время последнего изменения задачи", example = "2025-01-20T14:30:00Z")
    private Date lastChangeDate;

    /**
     * Идентификатор владельца задачи
     */
    @Schema(description = "ID пользователя, которому принадлежит задача", example = "42")
    private Long ownerId;

    /**
     * Настройки повторения задачи
     */
    @Schema(description = "Правила повторения задачи (если применимо)")
    private Repeat repeat;

    /**
     * Флаг, указывающий, включены ли уведомления для задачи
     */
    @Schema(description = "Включены ли уведомления о задаче", example = "true")
    private Boolean isNotificationEnabled;

    /**
     * Крайний срок выполнения задачи
     */
    @Schema(description = "Дата и время дедлайна задачи", example = "2025-02-01T18:00:00Z")
    private Date deadlineDate;

    /**
     * Флаг, указывающий, выполнена ли задача
     */
    @Schema(description = "Статус выполнения задачи", example = "false")
    private Boolean isCompleted;
}