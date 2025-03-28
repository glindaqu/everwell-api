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
    private Long productId;

    @Column
    private String title;

    @Column
    private Integer calories;

    @Column
    private Integer weightInGrams;

    @OneToMany(mappedBy = "products")
    private Set<Feed> feeds;
}
