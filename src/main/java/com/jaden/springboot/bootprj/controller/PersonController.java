package com.jaden.springboot.bootprj.controller;

import com.jaden.springboot.bootprj.domain.Person;
import com.jaden.springboot.bootprj.repository.PersonRepository;
import com.jaden.springboot.bootprj.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/person")
@Slf4j
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;


    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id){
        return personService.getPerson(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postPerson(@RequestBody Person person){
        personService.put(person);
        log.info("person -> {} ", personRepository.findAll());
    }
}
