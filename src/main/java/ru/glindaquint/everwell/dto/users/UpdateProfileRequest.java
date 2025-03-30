package ru.glindaquint.everwell.dto.users;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, yyyy hh:mm:ss a")
    private Date birthDate;

    private String sex;
    private String diseases;
    private List<String> badHabits;
}
