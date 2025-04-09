package ru.glindaquint.everwell.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Schema(description = "Параметры запроса")
public class RequestParams {
    @Schema(description = "Номер страницы", example = "1")
    private int page;

    @Schema(description = "Размер страницы", example = "10")
    private int size;
}
