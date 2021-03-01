package org.embl.co.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.embl.co.dto.request.PersonRequestDto;
import org.embl.co.dto.response.AllPersonResponse;
import org.embl.co.dto.response.PersonResponseDto;
import org.embl.co.entities.PersonEntity;
import org.embl.co.mapper.PersonMapper;
import org.embl.co.repository.PersonRepository;
import org.embl.co.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository repository;
    @Autowired
    private PersonMapper mapper;

    @Override
    public PersonResponseDto save(PersonRequestDto personDto) {
        log.info("Start PersonService -> save(...) Person");
        PersonEntity entity = repository.save(mapper.personEntityFromPersonDto(personDto));
        log.info("End PersonService -> save(...)");
        return mapper.personResponseDtoFromPersonEntity(entity);
    }

    @Override
    @Transactional
    public PersonResponseDto update(int id, PersonRequestDto personDto) {
        log.info("Start PersonService -> update(...) Person");
        PersonEntity entity = repository.findById(id).orElseThrow();
        if (personDto.getFirstName() != null) {
            entity.setFirstName(personDto.getFirstName());
        }
        if (personDto.getLastName() != null) {
            entity.setLastName(personDto.getLastName());
        }
        if (personDto.getAge() != null && personDto.getAge() > 0) {
            entity.setAge(personDto.getAge());
        }
        if (personDto.getFavouriteColor() != null) {
            entity.setFavouriteColor(personDto.getFavouriteColor());
        }
        PersonResponseDto response = mapper.personResponseDtoFromPersonEntity(entity);
        log.info("End PersonService -> update(...)");
        return response;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public PersonResponseDto findById(int id) {
        log.info("Start PersonService -> findById(...) personId : {}", id);
        PersonResponseDto response = repository.findById(id)
                .map(mapper::personResponseDtoFromPersonEntity)
                .orElseThrow();
        log.info("End PersonService -> findById(...)");
        return response;
    }

    @Override
    public AllPersonResponse findAll() {
        log.info("Start PersonService -> list() ");

        List<PersonResponseDto> response = repository.findAll()
                .stream()
                .map(mapper::personResponseDtoFromPersonEntity)
                .collect(Collectors.toList());
        log.info("End PersonService -> list(...)");
        return AllPersonResponse.builder()
                .personResponseDtoList(response)
                .build();
    }
}
