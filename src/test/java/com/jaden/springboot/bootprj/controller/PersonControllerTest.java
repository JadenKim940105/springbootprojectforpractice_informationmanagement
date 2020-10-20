package com.jaden.springboot.bootprj.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaden.springboot.bootprj.controller.dto.PersonDto;
import com.jaden.springboot.bootprj.domain.Person;
import com.jaden.springboot.bootprj.domain.dto.Birthday;
import com.jaden.springboot.bootprj.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.NestedServletException;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@Transactional
class PersonControllerTest {

    @Autowired
    private PersonController personController;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter massageConvert;

    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach(){
        mockMvc = MockMvcBuilders.standaloneSetup(personController).setMessageConverters(massageConvert).build();
    }

    @Test
    void getPerson() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("martin"))
                .andExpect(jsonPath("hobby").isEmpty())
                .andExpect(jsonPath("address").isEmpty())
                .andExpect(jsonPath("job").isEmpty())
                .andExpect(jsonPath("phoneNumber").isEmpty())
                .andExpect(jsonPath("deleted").value(false))
                .andExpect(jsonPath("age").isNumber())
                .andExpect(jsonPath("birthdayToday").isBoolean())
                .andExpect(jsonPath("$.birthDay").value("1991-08-15"))
                ;
    }

    @Test
    void postPerson() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"name\": \"martin2\",\n" +
                        "    \"age\": 20,\n" +
                        "    \"bloodType\": \"A\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void modifyPerson() throws Exception {
        PersonDto personDto = PersonDto.of("martin", "programming",
                "bundang", LocalDate.now(), "programmer","010-1111-2222");
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(toJsonString(personDto)))
                .andDo(print())
                .andExpect(status().isOk());

        Person result = personRepository.findById(1L).get();
        assertAll(
                ()-> assertThat(result.getName()).isEqualTo("martin"),
                ()-> assertThat(result.getAddress()).isEqualTo("bundang"),
                ()-> assertThat(result.getHobby()).isEqualTo("programming"),
                ()-> assertThat(result.getJob()).isEqualTo("programmer"),
                ()-> assertThat(result.getBirthDay()).isEqualTo(Birthday.of(LocalDate.now())),
                ()-> assertThat(result.getPhoneNumber()).isEqualTo("010-1111-2222")
        );
    }

    @Test
    void modifyPersonIfNameIsDifferent() throws Exception {
        PersonDto personDto = PersonDto.of("james", "programming",
                "bundang", LocalDate.now(), "programmer","010-1111-2222");
        assertThrows(NestedServletException.class, () ->
                mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/person/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(toJsonString(personDto)))
                        .andDo(print())
                        .andExpect(status().isOk())
        );
    }

    @Test
    void modifyName() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/person/1")
                .param("name", "martin2"))
                .andDo(print())
                .andExpect(status().isOk());
        assertThat(personRepository.findById(1L).get().getName()).isEqualTo("martin2");
    }

    @Test
    void deletePerson() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(personRepository.findById(1L).get().getDeleted()).isEqualTo(true);
    }

    private String toJsonString(PersonDto dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(dto);
    }
}