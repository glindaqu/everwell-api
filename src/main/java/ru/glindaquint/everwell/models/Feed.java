package ru.glindaquint.everwell.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ru.glindaquint.everwell.types.FeedType;

import java.util.Date;
import java.util.HashSet;
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

    @JsonIgnore
    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<FeedProduct> feedProducts = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    private void setDatetime() {
        setFeedDate(new Date());
    }

    public void addFeedProduct(FeedProduct feedProduct) {
        feedProducts.add(feedProduct);
        feedProduct.setFeed(this);
    }
}
