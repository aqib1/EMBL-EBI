package org.embl.co.service;

import org.embl.co.dto.request.PersonRequestDto;
import org.embl.co.dto.response.AllPersonResponse;
import org.embl.co.dto.response.PersonResponseDto;
import java.util.List;
public interface PersonService {
    PersonResponseDto save(PersonRequestDto personDto);

    PersonResponseDto update(int id, PersonRequestDto personDto);

    void delete(int id);

    PersonResponseDto findById(int id);

    AllPersonResponse findAll();
}
