package ru.glindaquint.everwell.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "bad_habits")
public class BadHabit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bad_habit_id_seq")
    @Column
    @SequenceGenerator(name = "bad_habit_id_seq", sequenceName = "bad_habit_id_seq", allocationSize = 1)
    private Long badHabitId;

    @Column(name = "title")
    private String title;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
