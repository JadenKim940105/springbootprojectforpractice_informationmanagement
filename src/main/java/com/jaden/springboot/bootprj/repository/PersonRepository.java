package com.jaden.springboot.bootprj.repository;

import com.jaden.springboot.bootprj.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository //jpaRepository 를 extends 하면 자동으로 repository bean으로 등록됨
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByName(String name);
    List<Person> findByBlockIsNull();
    List<Person> findByBloodType(String bloodType);


    @Query(value = "select person from Person person where person.birthDay.monthOfBirthday = :monthOfBirthday")
    List<Person> findByMonthOfBirthDay(@Param("monthOfBirthday") int monthOfBirthday);
}
