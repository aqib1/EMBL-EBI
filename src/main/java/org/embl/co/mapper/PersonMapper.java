package org.embl.co.mapper;

import org.embl.co.dto.request.PersonRequestDto;
import org.embl.co.dto.response.PersonResponseDto;
import org.embl.co.entities.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    @Mapping(source = "entity.id",target = "personId")
    PersonResponseDto personResponseDtoFromPersonEntity(PersonEntity entity);

    PersonEntity personEntityFromPersonDto(PersonRequestDto personDto);

    List<PersonResponseDto> personResponseDtoListFromEntities(List<PersonEntity> entities);
}
