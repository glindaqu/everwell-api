package ru.glindaquint.everwell.dto.requests.bloodPressure;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO запроса для добавления данных об артериальном давлении.
 * Содержит показатели систолического, диастолического давления и пульса.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Запрос на добавление показателей артериального давления")
public class InsertBloodPressureRequest {

    /**
     * Систолическое давление (верхний показатель)
     * Измеряется в мм рт. ст.
     */
    @NotNull(message = "Показатель систолического давления обязателен")
    @Min(value = 70, message = "Систолическое давление не может быть меньше 70 мм рт. ст.")
    @Max(value = 250, message = "Систолическое давление не может превышать 250 мм рт. ст.")
    @Schema(
            description = "Систолическое давление (верхний показатель) в мм рт. ст.",
            example = "120",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minimum = "70",
            maximum = "250"
    )
    private Integer systolicPressure;

    /**
     * Диастолическое давление (нижний показатель)
     * Измеряется в мм рт. ст.
     */
    @NotNull(message = "Показатель диастолического давления обязателен")
    @Min(value = 40, message = "Диастолическое давление не может быть меньше 40 мм рт. ст.")
    @Max(value = 150, message = "Диастолическое давление не может превышать 150 мм рт. ст.")
    @Schema(
            description = "Диастолическое давление (нижний показатель) в мм рт. ст.",
            example = "80",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minimum = "40",
            maximum = "150"
    )
    private Integer diastolicPressure;

    /**
     * Частота сердечных сокращений (пульс)
     * Измеряется в ударах в минуту
     */
    @NotNull(message = "Показатель пульса обязателен")
    @Min(value = 30, message = "Пульс не может быть меньше 30 ударов в минуту")
    @Max(value = 200, message = "Пульс не может превышать 200 ударов в минуту")
    @Schema(
            description = "Частота сердечных сокращений (ударов в минуту)",
            example = "75",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minimum = "30",
            maximum = "200"
    )
    private Integer heartRate;
}