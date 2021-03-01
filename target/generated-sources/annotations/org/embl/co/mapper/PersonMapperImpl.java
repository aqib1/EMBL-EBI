package org.embl.co.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.embl.co.dto.request.PersonRequestDto;
import org.embl.co.dto.response.PersonResponseDto;
import org.embl.co.dto.response.PersonResponseDto.PersonResponseDtoBuilder;
import org.embl.co.entities.PersonEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-01T04:13:32+0100",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public PersonResponseDto personResponseDtoFromPersonEntity(PersonEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PersonResponseDtoBuilder personResponseDto = PersonResponseDto.builder();

        personResponseDto.personId( entity.getId() );
        personResponseDto.firstName( entity.getFirstName() );
        personResponseDto.lastName( entity.getLastName() );
        personResponseDto.age( entity.getAge() );
        personResponseDto.favouriteColor( entity.getFavouriteColor() );

        return personResponseDto.build();
    }

    @Override
    public PersonEntity personEntityFromPersonDto(PersonRequestDto personDto) {
        if ( personDto == null ) {
            return null;
        }

        PersonEntity personEntity = new PersonEntity();

        personEntity.setFirstName( personDto.getFirstName() );
        personEntity.setLastName( personDto.getLastName() );
        personEntity.setAge( personDto.getAge() );
        personEntity.setFavouriteColor( personDto.getFavouriteColor() );

        return personEntity;
    }

    @Override
    public List<PersonResponseDto> personResponseDtoListFromEntities(List<PersonEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PersonResponseDto> list = new ArrayList<PersonResponseDto>( entities.size() );
        for ( PersonEntity personEntity : entities ) {
            list.add( personResponseDtoFromPersonEntity( personEntity ) );
        }

        return list;
    }
}
