package org.embl.co.controller;

import io.swagger.annotations.Api;
import org.embl.co.dto.request.PersonRequestDto;
import org.embl.co.dto.response.AllPersonResponse;
import org.embl.co.dto.response.PersonResponseDto;
import org.embl.co.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/persons")
@Api(value = "Person Controller")
public class PersonController {
    @Autowired
    private PersonServiceImpl service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PersonResponseDto> savePerson(@RequestBody @Valid PersonRequestDto personDto) {
        return new ResponseEntity<>(service.save(personDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PersonResponseDto> updatePerson(@PathVariable("id") Integer id, @RequestBody PersonRequestDto personDto) {
        return ResponseEntity.ok(service.update(id, personDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable("id") Integer id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PersonResponseDto> getPerson(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AllPersonResponse> getAllPersons() {
        return ResponseEntity.ok(service.findAll());
    }
}
