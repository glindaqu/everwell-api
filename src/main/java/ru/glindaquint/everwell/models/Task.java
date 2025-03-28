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

    @Column(name = "repeat", nullable = false)
    @Enumerated(EnumType.STRING)
    private Repeat repeat;

    @Column(name = "is_notification_enabled", nullable = false)
    private Boolean isNotificationEnabled;

    @Column(name = "deadline_date", nullable = false)
    private Date deadlineDate;

    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted;

    // foreign key
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    private void beforeInsert() {
        Date date = new Date();
        this.creationDate = date;
        this.lastChangeDate = date;

        this.isCompleted = false;
    }
}
