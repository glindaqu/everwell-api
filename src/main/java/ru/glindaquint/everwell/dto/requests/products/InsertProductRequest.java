package ru.glindaquint.everwell.dto.requests.products;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO запроса для добавления нового продукта в систему.
 * Содержит все необходимые данные о пищевой ценности продукта.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Запрос на добавление нового продукта питания")
public class InsertProductRequest {

    /**
     * Название продукта
     */
    @NotBlank(message = "Название продукта обязательно для заполнения")
    @Size(min = 2, max = 100, message = "Название продукта должно содержать от 2 до 100 символов")
    @Schema(
            description = "Название продукта питания",
            example = "Яблоко Голден",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 2,
            maxLength = 100
    )
    private String title;

    /**
     * Вес продукта в граммах
     */
    @NotNull(message = "Вес продукта обязателен для заполнения")
    @Min(value = 1, message = "Вес продукта должен быть не менее 1 грамма")
    @Max(value = 10000, message = "Вес продукта не может превышать 10 кг (10000 г)")
    @Schema(
            description = "Вес продукта в граммах",
            example = "150",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minimum = "1",
            maximum = "10000"
    )
    private Integer weightInGrams;

    /**
     * Калорийность продукта (ккал)
     */
    @NotNull(message = "Калорийность продукта обязательна для заполнения")
    @Min(value = 1, message = "Калорийность должна быть не менее 1 ккал")
    @Max(value = 1000, message = "Калорийность не может превышать 1000 ккал на 100г")
    @Schema(
            description = "Калорийность продукта в ккал",
            example = "52",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minimum = "1",
            maximum = "1000"
    )
    private Integer calories;

    /**
     * Содержание белка (г)
     */
    @NotNull(message = "Содержание белка обязательно для заполнения")
    @DecimalMin(value = "0.0", message = "Содержание белка не может быть отрицательным")
    @DecimalMax(value = "100.0", message = "Содержание белка не может превышать 100 г")
    @Schema(
            description = "Содержание белка в граммах",
            example = "0.3",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minimum = "0.0",
            maximum = "100.0"
    )
    private Float protein;

    /**
     * Содержание жиров (г)
     */
    @NotNull(message = "Содержание жиров обязательно для заполнения")
    @DecimalMin(value = "0.0", message = "Содержание жиров не может быть отрицательным")
    @DecimalMax(value = "100.0", message = "Содержание жиров не может превышать 100 г")
    @Schema(
            description = "Содержание жиров в граммах",
            example = "0.2",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minimum = "0.0",
            maximum = "100.0"
    )
    private Float fat;

    /**
     * Содержание углеводов (г)
     */
    @NotNull(message = "Содержание углеводов обязательно для заполнения")
    @DecimalMin(value = "0.0", message = "Содержание углеводов не может быть отрицательным")
    @DecimalMax(value = "100.0", message = "Содержание углеводов не может превышать 100 г")
    @Schema(
            description = "Содержание углеводов в граммах",
            example = "13.8",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minimum = "0.0",
            maximum = "100.0"
    )
    private Float carbohydrates;
}