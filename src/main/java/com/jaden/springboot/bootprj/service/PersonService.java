package com.jaden.springboot.bootprj.service;

import com.jaden.springboot.bootprj.domain.Person;
import com.jaden.springboot.bootprj.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    //차단된 사람을 제외한 모든 사람을 불러오는 메소드
    public List<Person> getPeopleExcludeBlocks(){
        /*List<Person> people = personRepository.findAll();
        return people.stream().filter(person -> person.getBlock()==null).collect(Collectors.toList());*/
        return personRepository.findByBlockIsNull();
    }



    @Transactional(readOnly = true )
    public Person getPerson(Long id){
        Person person = personRepository.findById(id).get();

        log.info("person : {}", person);

        return person;
    }

    public List<Person> getPeopleByName(String name) {
    /*    List<Person> people = personRepository.findAll();
        return people.stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());*/
    return personRepository.findByName(name);
    }
}
