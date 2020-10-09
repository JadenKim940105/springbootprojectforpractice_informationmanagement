package com.jaden.springboot.bootprj.service;

import com.jaden.springboot.bootprj.domain.Block;
import com.jaden.springboot.bootprj.domain.Person;
import com.jaden.springboot.bootprj.repository.BlockRepository;
import com.jaden.springboot.bootprj.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BlockRepository blockRepository;

    @Test
    void getPeopleExcludeBlocks(){
        givenPeople();
        givenBlocks();

        List<Person> result = personService.getPeopleExcludeBlocks();

        //System.out.println(result);
        result.forEach(System.out::println);
    }

    private void givenBlocks() {
        givenBlock("jaden");
    }

    private Block givenBlock(String name) {
        return blockRepository.save(new Block(name));
    }

    private void givenBlockPerson(String name, int age, String bloodType){
        Person blockPerson = new Person(name, age, bloodType);
        blockPerson.setBlock(givenBlock(name));
        personRepository.save(blockPerson);
    }

    private void givenPeople() {
        givenPerson("jaden", 10, "B");
        givenPerson("david", 9, "B");
        givenPerson("dennis", 7, "O");
        givenBlockPerson("jaden", 11, "AB");
    }

    private void givenPerson(String name, int age, String bloodType) {
        personRepository.save(new Person(name, age, bloodType));
    }
}