package ru.glindaquint.everwell.dto.responses;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Meta {
    private Date timestamp;
    private String version;
}
