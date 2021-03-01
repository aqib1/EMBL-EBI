package org.embl.co.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.embl.co.dto.base.Base;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class PersonRequestDto extends Base {
    @JsonProperty("first_name")
    @NotNull(message = "First name is required")
    private String firstName;

    @JsonProperty("last_name")
    @NotNull(message = "Last name is required")
    private String lastName;

    @JsonProperty("age")
    @NotNull(message = "Age is required")
    private Integer age;

    @JsonProperty("favourite_color")
    @NotNull(message = "Favourite color is required")
    private String favouriteColor;
}
