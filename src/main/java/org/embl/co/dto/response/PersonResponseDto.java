package org.embl.co.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class PersonResponseDto {

    private int personId;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private int age;

    @JsonProperty("favourite_color")
    private String favouriteColor;
}
