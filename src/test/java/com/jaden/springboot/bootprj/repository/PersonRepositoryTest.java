package com.jaden.springboot.bootprj.repository;

import com.jaden.springboot.bootprj.domain.Person;
import com.jaden.springboot.bootprj.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonService personService;

    @Transactional
    @Test
    void crud(){
        Person person = new Person();
        person.setName("jaden");
        person.setDeleted(false);

        personRepository.save(person);

        List<Person> result = personRepository.findByName("jaden");

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("jaden");
//      assertThat(result.get(0).getAge()).isEqualTo(10);
    }


    @Test
    void findByBirthDayBetween(){

        List<Person> result = personRepository.findByMonthOfBirthDay(8);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("sophia");
    }
}