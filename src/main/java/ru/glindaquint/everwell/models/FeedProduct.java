package ru.glindaquint.everwell.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "feeds_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedProduct {
    @Id
    @Column(name = "feed_product_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feed_product_id_seq")
    @SequenceGenerator(name = "feed_product_id_seq", sequenceName = "feed_product_id_seq", allocationSize = 1)
    private Long feed_product_id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;
    private Integer weightInGrams;
    private Float protein;
    private Float carbohydrates;
    private Float fat;
    private Integer calories;
}