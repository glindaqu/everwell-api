package ru.glindaquint.everwell.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1)
    private Long productId;

    @Column(name = "title")
    private String title;

    @Column(name = "calories")
    private Integer calories;

    @Column(name = "weight_in_grams")
    private Integer weightInGrams;

    @Column(name = "protein")
    private Float protein;

    @Column(name = "fat")
    private Float fat;

    @Column(name = "carbohydrates")
    private Float carbohydrates;

    @OneToMany(mappedBy = "products")
    private Set<Feed> feeds;
}
