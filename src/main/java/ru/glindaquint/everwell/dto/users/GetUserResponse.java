package ru.glindaquint.everwell.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@Schema
public class GetUserResponse {
    private String username;
    private String email;
}
