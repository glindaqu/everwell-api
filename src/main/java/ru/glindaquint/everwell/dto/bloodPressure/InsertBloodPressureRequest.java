package ru.glindaquint.everwell.dto.bloodPressure;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema
public class InsertBloodPressureRequest {
    private Integer systolicPressure;
    private Integer diastolicPressure;
    private Integer heartRate;
}
