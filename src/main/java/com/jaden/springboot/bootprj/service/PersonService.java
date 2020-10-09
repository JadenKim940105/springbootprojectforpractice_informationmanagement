package com.jaden.springboot.bootprj.service;

import com.jaden.springboot.bootprj.domain.Person;
import com.jaden.springboot.bootprj.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    //차단된 사람을 제외한 모든 사람을 불러오는 메소드
    public List<Person> getPeopleExcludeBlocks(){
        List<Person> people = personRepository.findAll();

        return people.stream().filter(person -> person.getBlock()==null).collect(Collectors.toList());
    }
}
