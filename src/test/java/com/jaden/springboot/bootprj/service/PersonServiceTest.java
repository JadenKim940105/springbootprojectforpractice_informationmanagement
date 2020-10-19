package com.jaden.springboot.bootprj.service;

import com.jaden.springboot.bootprj.domain.Person;
import com.jaden.springboot.bootprj.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    private PersonService personService;

    @Test
    void getPeopleExcludeBlocks(){
        List<Person> result = personService.getPeopleExcludeBlocks();
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("david");
        assertThat(result.get(2).getName()).isEqualTo("benny");
    }

    @Test
    void getPeopleByName(){
        List<Person> people = personService.getPeopleByName("martin");
        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("martin");
    }

    @Test
    void getPerson(){
        Person person = personService.getPerson(3L);
        assertThat(person.getName()).isEqualTo("dennis");
    }
}