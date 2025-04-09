package ru.glindaquint.everwell.dto.requests.products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InsertProductRequest {
    private String title;
    private Integer weightInGrams;
    private Integer calories;
    private Float protein;
    private Float fat;
    private Float carbohydrates;
}
