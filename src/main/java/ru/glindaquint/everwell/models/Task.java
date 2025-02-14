package ru.glindaquint.everwell.models;

import jakarta.persistence.*;
import lombok.*;
import ru.glindaquint.everwell.types.Repeat;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tasks")
public class Task {
    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_id_seq")
    @SequenceGenerator(name = "task_id_seq", sequenceName = "task_id_seq", allocationSize = 1)
    private Long taskId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "last_change_date", nullable = false)
    private Date lastChangeDate;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column(name = "repeat", nullable = false)
    @Enumerated(EnumType.STRING)
    private Repeat repeat;

    @Column(name = "is_notification_enabled", nullable = false)
    private Boolean isNotificationEnabled;

    @Column(name = "deadline_date", nullable = false)
    private Date deadlineDate;

    @PrePersist
    private void beforeInsert() {
        Date date = new Date();
        this.creationDate = date;
        this.lastChangeDate = date;
    }
}
