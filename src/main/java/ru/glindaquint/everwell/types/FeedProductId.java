package ru.glindaquint.everwell.types;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedProductId implements Serializable {
    @Column(name = "feed_id")
    private Long feedId;

    @Column(name = "product_id")
    private Long productId;
}