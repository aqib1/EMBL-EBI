package org.embl.co.integeration.controller;

import org.embl.co.dto.response.AllPersonResponse;
import org.embl.co.dto.response.PersonResponseDto;
import org.embl.co.helper.JsonHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
public class PersonControllerIT implements JsonHelper {
    public static final int AGE = 29;
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Keynes";
    public static final String FAVOURITE_COLOR = "red";
    public static final int ID = 1;

    private static final String PERSON_ENDPOINT = "/api/persons";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_ShouldCreatePerson() throws Exception {
        PersonResponseDto request = PersonResponseDto.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .age(AGE)
                .favouriteColor(FAVOURITE_COLOR)
                .build();
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post(PERSON_ENDPOINT + "/")
                        .content(mapToJson(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertNotNull(mvcResult);
        Assertions.assertEquals(HttpStatus.CREATED, HttpStatus.valueOf(mvcResult.getResponse().getStatus()));

    }

    @Test
    public void updatePerson() throws Exception {
        PersonResponseDto request = PersonResponseDto.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .age(AGE)
                .favouriteColor(FAVOURITE_COLOR)
                .build();
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.put(PERSON_ENDPOINT + "/" + ID)
                        .content(mapToJson(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertNotNull(mvcResult);
        Assertions.assertEquals(HttpStatus.OK, HttpStatus.valueOf(mvcResult.getResponse().getStatus()));

    }

    @Test
    public void testDeletePerson() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.delete(PERSON_ENDPOINT + "/2")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }


    @Test
    public void testGetPersonShouldReturn() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get(PERSON_ENDPOINT + "/" + ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        Assertions.assertNotNull(mvcResult);
        Assertions.assertEquals(HttpStatus.OK, HttpStatus.valueOf(mvcResult.getResponse().getStatus()));

        PersonResponseDto personDTO = mapFromJson(mvcResult.getResponse().getContentAsString(), PersonResponseDto.class);
        Assertions.assertNotNull(personDTO);

    }

    @Test
    public void testGetPersonShouldNotReturn() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get(PERSON_ENDPOINT + "/-1" )
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getAllPersons() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get(PERSON_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        Assertions.assertNotNull(mvcResult);
        Assertions.assertEquals(HttpStatus.OK, HttpStatus.valueOf(mvcResult.getResponse().getStatus()));

        AllPersonResponse personsDTO = mapFromJson(mvcResult.getResponse().getContentAsString(), AllPersonResponse.class);
        Assertions.assertFalse(personsDTO.getPersonResponseDtoList().isEmpty());

    }
}
