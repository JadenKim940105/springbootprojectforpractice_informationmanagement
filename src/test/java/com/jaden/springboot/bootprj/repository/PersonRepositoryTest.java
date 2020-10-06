package com.jaden.springboot.bootprj.repository;

import com.jaden.springboot.bootprj.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud(){
        Person person = new Person();
        person.setName("jaden");
        person.setAge(10);

        personRepository.save(person);

        //System.out.println(personRepository.findAll());
        List<Person> people = personRepository.findAll();

        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("jaden");
        assertThat(people.get(0).getAge()).isEqualTo(10);
    }

}