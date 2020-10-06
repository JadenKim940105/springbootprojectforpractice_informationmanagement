package com.jaden.springboot.bootprj.repository;

import com.jaden.springboot.bootprj.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
