package com.jaden.springboot.bootprj.service;

import com.jaden.springboot.bootprj.common.Common;
import com.jaden.springboot.bootprj.controller.dto.PersonDto;
import com.jaden.springboot.bootprj.domain.Person;
import com.jaden.springboot.bootprj.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private Common common;

    //차단된 사람을 제외한 모든 사람을 불러오는 메소드
    public List<Person> getPeopleExcludeBlocks(){
        /*List<Person> people = personRepository.findAll();
        return people.stream().filter(person -> person.getBlock()==null).collect(Collectors.toList());*/
        return personRepository.findByBlockIsNull();
    }



    @Transactional(readOnly = true )
    public Person getPerson(Long id){
        // Person person = personRepository.findById(id).get();
        Person person =personRepository.findById(id).orElse(null);

        log.info("person : {}", person);

        return person;
    }

    public List<Person> getPeopleByName(String name) {
    /*    List<Person> people = personRepository.findAll();
        return people.stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());*/
    return personRepository.findByName(name);
    }

    @Transactional
    public void put(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, PersonDto personDto) {
        Person person = personRepository.findById(id).orElseThrow(() -> common.noIdException());

        if(!person.getName().equals(personDto.getName())){
             throw new RuntimeException("이름이 다릅니다.");
        }

        person.set(personDto);

        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, String name){
        Person person = personRepository.findById(id).orElseThrow(() -> common.noIdException());

        person.setName(name);

        personRepository.save(person);
    }

    @Transactional
    public void delete(Long id) {
        //personRepository.deleteById(id);   -> 데이터를 날려벌면 복구할 방법이 없음으로 flag 를 바꾼느 형식을 사용한다.
        Person person = personRepository.findById(id).orElseThrow(() -> common.noIdException());
        person.setDeleted(true);
        personRepository.save(person);
    }
}
