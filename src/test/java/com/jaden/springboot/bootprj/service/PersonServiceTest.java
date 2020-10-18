package com.jaden.springboot.bootprj.service;

import com.jaden.springboot.bootprj.domain.Block;
import com.jaden.springboot.bootprj.domain.Person;
import com.jaden.springboot.bootprj.repository.BlockRepository;
import com.jaden.springboot.bootprj.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
        List<Person> result = personService.getPeopleExcludeBlocks();
        result.forEach(System.out::println);
    }

    @Test
    void findByBloodType(){
        givenPerson("jaden", 10, "B", LocalDate.of(1991,8,12));
        givenPerson("david", 9, "B", LocalDate.of(1992,1,2));
        givenPerson("dennis", 7, "O", LocalDate.of(1991,3,29));
        List<Person> result = personRepository.findByBloodType("A");

        result.forEach(System.out::println);

    }

    @Test
    void findByBirthDayBetween(){
        givenPerson("jaden", 10, "B", LocalDate.of(1991,8,12));
        givenPerson("david", 9, "B", LocalDate.of(1992,1,2));
        givenPerson("dennis", 7, "O", LocalDate.of(1991,3,29));

        List<Person> people = personRepository.findByBirthDayBetween
                (LocalDate.of(1991,8,1), LocalDate.of(1991,8,31));

        people.forEach(System.out::println);

    }



    @Test
    void getPeopleByName(){
        givenPeople();
        List<Person> people = personService.getPeopleByName("jaden");
        people.forEach(System.out::println);
    }

    @Test
    void cascadeTest (){
        givenPeople();

        List<Person> result = personRepository.findAll();

        result.forEach(System.out::println);

        Person person = result.get(3);
        person.getBlock().setStartDate(LocalDate.now());
        person.getBlock().setEndDate(LocalDate.now());

        personRepository.save(person);
        personRepository.findAll().forEach(System.out::println);

        /*personRepository.delete(person);
        personRepository.findAll().forEach(System.out::println);
        blockRepository.findAll().forEach(System.out::println);*/

        person.setBlock(null);
        personRepository.save(person);
        personRepository.findAll().forEach(System.out::println);
        blockRepository.findAll().forEach(System.out::println);


    }

    @Test
    void casecadeTest(){
        givenPeople();
        List<Person> result =  personRepository.findAll();

        result.forEach(System.out::println);

        Person person = result.get(3);
        person.getBlock().setStartDate(LocalDate.now());
        person.getBlock().setEndDate(LocalDate.now());

        personRepository.save(person);
        personRepository.findAll().forEach(System.out::println);

        personRepository.delete(person);
        personRepository.findAll().forEach(System.out::println);
        blockRepository.findAll().forEach(System.out::println);

    }

    private void givenBlockPerson(String name, int age, String bloodType){
        Person blockPerson = new Person(name, age, bloodType);
        blockPerson.setBlock(new Block(name));
        personRepository.save(blockPerson);
    }


    @Test
    void getPerson(){
        givenPeople();
        Person person = personService.getPerson(4L);

        System.out.println(person);
    }


    private void givenPeople() {
        givenPerson("jaden", 10, "B", LocalDate.of(1991,8,12));
        givenPerson("david", 9, "B", LocalDate.of(1992,1,2));
        givenPerson("dennis", 7, "O", LocalDate.of(1991,3,29));
    }

    private void givenPerson(String name, int age, String bloodType, LocalDate birthDay) {
        Person person = new Person(name, age, bloodType);
        person.setBirthDay(birthDay);
        personRepository.save(person);
    }
}