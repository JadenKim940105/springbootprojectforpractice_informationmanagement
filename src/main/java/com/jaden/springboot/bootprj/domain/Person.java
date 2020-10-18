package com.jaden.springboot.bootprj.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private int age;
    private String hobby;
    @NonNull
    private String bloodType;
    private String address;
    private LocalDate birthDay;
    private String job;
    @ToString.Exclude
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Block block;




    /*public boolean equals(Object object){
        if(object == null)
            return false;

        Person person = (Person) object;

        if(!person.getName().equals(this.getName()))
            return false;
        if(person.getAge() != this.getAge())
            return false;
        else return true;
    }*/
}
