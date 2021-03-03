package org.embl.co.integeration.business;
import org.embl.co.dto.request.PersonRequestDto;
import org.embl.co.dto.response.PersonResponseDto;
import org.embl.co.entities.PersonEntity;
import org.embl.co.mapper.PersonMapper;
import org.embl.co.repository.PersonRepository;
import org.embl.co.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
public class PersonServiceImplTest {
    public static final int AGE = 29;
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Keynes";
    public static final String FAVOURITE_COLOR = "red";
    public static final int ID = 1;

    @Mock
    private PersonRepository personRepository;

    private PersonServiceImpl underTestService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PersonMapper personMapper = Mappers.getMapper(PersonMapper.class);
        underTestService = new PersonServiceImpl(personRepository, personMapper);
    }

    @Test
    public void testSavePerson() {
        PersonRequestDto request = build();
        PersonEntity entity = buildEntity();
        PersonResponseDto expectedResponse = buildExpected();
        when(personRepository.save(ArgumentMatchers.any())).thenReturn(entity);
        PersonResponseDto actualResponse = underTestService.save(request);
        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(expectedResponse.getAge(), actualResponse.getAge());
        Assertions.assertEquals(expectedResponse.getFavouriteColor(), actualResponse.getFavouriteColor());
        Assertions.assertEquals(expectedResponse.getFirstName(), actualResponse.getFirstName());
        Assertions.assertEquals(expectedResponse.getLastName(), actualResponse.getLastName());
    }

    @Test
    public void testUpdatePerson() {
        PersonRequestDto request = build();
        request.setAge(10);
        PersonEntity entity = buildEntity();
        entity.setAge(10);
        PersonResponseDto expectedResponse = new PersonResponseDto();
        expectedResponse.setAge(10);
        when(personRepository.findById(ID)).thenReturn(Optional.of(entity));
        PersonResponseDto actualResponse = underTestService.update(ID, request);
        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(expectedResponse.getAge(), actualResponse.getAge());
    }

    @Test
    public void testDeletePerson() {
        doNothing().when(personRepository).deleteById(ID);
        underTestService.delete(ID);
        verify(personRepository).deleteById(ID);
    }

    @Test
    public void testFindByIdPerson() {
        PersonEntity entity = buildEntity();
        PersonResponseDto expectedResponse = buildExpected();
        when(personRepository.findById(ID)).thenReturn(Optional.of(entity));
        PersonResponseDto actualResponse = underTestService.findById(ID);
        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(expectedResponse.getAge(), actualResponse.getAge());
        Assertions.assertEquals(expectedResponse.getFavouriteColor(), actualResponse.getFavouriteColor());
        Assertions.assertEquals(expectedResponse.getFirstName(), actualResponse.getFirstName());
        Assertions.assertEquals(expectedResponse.getLastName(), actualResponse.getLastName());
    }

    @Test
    public void testListPerson() {
        PersonEntity entity = buildEntity();
        PersonResponseDto expectedResponse = buildExpected();
        when(personRepository.findAll()).thenReturn(List.of(entity));
        List<PersonResponseDto> actualResponse = underTestService.findAll().getPersonResponseDtoList();
        Assertions.assertNotNull(actualResponse);
        Assertions.assertTrue(actualResponse.size() > 0);
        Assertions.assertEquals(expectedResponse.getAge(), actualResponse.get(0).getAge());
        Assertions.assertEquals(expectedResponse.getFavouriteColor(), actualResponse.get(0).getFavouriteColor());
        Assertions.assertEquals(expectedResponse.getFirstName(), actualResponse.get(0).getFirstName());
        Assertions.assertEquals(expectedResponse.getLastName(), actualResponse.get(0).getLastName());
    }

    private PersonRequestDto build() {
        return PersonRequestDto.builder()
                .age(AGE)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .favouriteColor(FAVOURITE_COLOR)
                .build();
    }

    private PersonEntity buildEntity() {
        PersonEntity entity = new PersonEntity();
        entity.setFirstName(FIRST_NAME);
        entity.setLastName(LAST_NAME);
        entity.setAge(AGE);
        entity.setFavouriteColor(FAVOURITE_COLOR);
        entity.setId(ID);
        return entity;
    }

    private PersonResponseDto buildExpected() {
        PersonResponseDto expectedResponse = new PersonResponseDto();
        expectedResponse.setAge(AGE);
        expectedResponse.setFavouriteColor(FAVOURITE_COLOR);
        expectedResponse.setLastName(LAST_NAME);
        expectedResponse.setFirstName(FIRST_NAME);
        return expectedResponse;
    }
}
