package ru.glindaquint.everwell.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Универсальный контейнер для API-запросов с пагинацией и метаданными.
 *
 * @param <T> тип передаваемых данных
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Стандартный контейнер для API-запросов")
public class DataRequest<T> {

    /**
     * Основные данные запроса
     */
    @Schema(
            description = "Полезная нагрузка запроса",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "{\"title\": \"Новая задача\"}"
    )
    private T data;

    /**
     * Параметры запроса (сортировка, фильтрация и т.д.)
     */
    @Schema(
            description = "Дополнительные параметры запроса",
            nullable = true,
            example = "{\"page\": 1, \"size\": 20}"
    )
    private RequestParams params;
}
