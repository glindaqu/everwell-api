package ru.glindaquint.everwell.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Универсальный контейнер для всех успешных ответов API.
 * Обеспечивает стандартную структуру ответа с данными, мета-информацией и ошибками.
 *
 * @param <T> тип возвращаемых данных
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Стандартный контейнер для API-ответов")
public class DataResponse<T> {

    /**
     * Основные данные ответа.
     * Может содержать объект, список объектов или примитивное значение.
     */
    @Schema(
            description = "Полезные данные ответа",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "{\"id\": 1, \"title\": \"Пример задачи\"}"
    )
    private T data;

    /**
     * Мета-информация о запросе.
     * Включает технические данные (время обработки, пагинацию и т.д.)
     */
    @Schema(
            description = "Техническая информация о запросе",
            nullable = true,
            example = "{\"timestamp\": \"2025-01-01T00:00:00Z\", \"version\": \"1.0\"}"
    )
    private Meta meta;

    /**
     * Информация об ошибках (для частично успешных запросов).
     * При полном успехе содержит null.
     */
    @Schema(
            description = "Информация об ошибках (если запрос выполнен частично)",
            nullable = true,
            example = "Не все данные были обработаны"
    )
    private String error;
}
