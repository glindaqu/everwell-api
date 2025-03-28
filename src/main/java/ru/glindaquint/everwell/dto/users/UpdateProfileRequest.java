package ru.glindaquint.everwell.dto.users;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileRequest {
    private String lastname;
    private String firstname;
    private String patronymic;
    private Integer height;
    private Integer weight;
    private LocalDate birthDate;
    private String sex;
    private String diseases;
    private List<String> badHabits;
}
