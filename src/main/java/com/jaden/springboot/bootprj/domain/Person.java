package com.jaden.springboot.bootprj.domain;

import com.jaden.springboot.bootprj.domain.dto.BirthDay;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NonNull
    @Min(1)
    @Column(nullable = false)
    private int age;

    private String hobby;

    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String bloodType;
    
    private String address;

    @Valid
    @Embedded
    private BirthDay birthDay;

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
