package org.embl.co.dto.response;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class AllPersonResponse {
    private List<PersonResponseDto> personResponseDtoList;
}
