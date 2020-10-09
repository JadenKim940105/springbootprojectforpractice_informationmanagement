package com.jaden.springboot.bootprj.repository;

import com.jaden.springboot.bootprj.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //jpaRepository 를 extends 하면 자동으로 repository bean으로 등록됨
public interface PersonRepository extends JpaRepository<Person, Long> {

}
