package org.embl.co.dto.response;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class AllPersonResponse {
    private List<PersonResponseDto> personResponseDtoList;
}
