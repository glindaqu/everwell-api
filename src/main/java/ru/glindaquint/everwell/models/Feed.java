package ru.glindaquint.everwell.models;

import jakarta.persistence.*;
import lombok.*;
import ru.glindaquint.everwell.types.FeedType;

import java.util.Date;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "feeds")
public class Feed {
    @Id
    @Column(name = "feed_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feed_id_seq")
    @SequenceGenerator(name = "feed_id_seq", sequenceName = "feed_id_seq", allocationSize = 1)
    private Long feedId;

    @Column(name = "feed_date")
    private Date feedDate;

    @Column(name = "feed_type")
    @Enumerated(value = EnumType.STRING)
    private FeedType feedType;

    @OneToMany
    private Set<Product> products;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
