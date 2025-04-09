package ru.glindaquint.everwell.dto.responses.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@Getter
@Setter
@Schema
public class GetUserResponse {
    private String username;
    private String email;
    private String lastname;
    private String firstname;
    private String patronymic;
    private String sex;
    private String diseases;
    private Date birthDate;
    private Integer weight;
    private Integer height;
    private Set<String> badHabits;
}
