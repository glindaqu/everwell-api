package ru.glindaquint.everwell.dto.responses.bloodPressure;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.glindaquint.everwell.models.BloodPressure;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema
public class GetUsersBloodPressuresResponse {
    private List<BloodPressure> bloodPressures;
}
