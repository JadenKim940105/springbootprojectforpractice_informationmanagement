package com.jaden.springboot.bootprj.domain;

import com.jaden.springboot.bootprj.controller.dto.PersonDto;
import com.jaden.springboot.bootprj.domain.dto.BirthDay;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Where(clause = "deleted = false")   // Person 과 관련된 query 가 날라갈때 기본적으로 deleted = false 인것으로..
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

    @ColumnDefault("0")
    private Boolean deleted;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Block block;



    public void set(PersonDto personDto){
        if( personDto.getAge() != 0){
            this.setAge(personDto.getAge());
        }
        if (!StringUtils.isEmpty(personDto.getHobby())){
            this.setHobby(personDto.getHobby());
        }
        if (!StringUtils.isEmpty(personDto.getBloodType())) {
            this.setBloodType(personDto.getBloodType());
        }
        if(!StringUtils.isEmpty(personDto.getAddress())){
            this.setAddress(personDto.getAddress());
        }
        if(!StringUtils.isEmpty(personDto.getJob())){
            this.setJob(personDto.getJob());
        }
        if(!StringUtils.isEmpty(personDto.getPhoneNumber())){
            this.setPhoneNumber(personDto.getPhoneNumber());
        }
    }

}
