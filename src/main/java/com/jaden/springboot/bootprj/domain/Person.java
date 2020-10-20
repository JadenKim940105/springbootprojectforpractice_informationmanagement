package com.jaden.springboot.bootprj.domain;

import com.jaden.springboot.bootprj.controller.dto.PersonDto;
import com.jaden.springboot.bootprj.domain.dto.Birthday;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;
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
@Where(clause = "deleted = false")   // Person 과 관련된 query 가 날라갈때 기본적으로 deleted = false 인것으로..
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @Valid
    @Embedded
    private Birthday birthDay;

    private String hobby;

    private String address;

    private String job;

    private String phoneNumber;

    @ColumnDefault("0")
    private Boolean deleted;

    public Integer getAge(){
        if(this.birthDay != null){
            return LocalDate.now().getYear() - this.birthDay.getYearOfBirthday() + 1;
        }
        return null;
    }

    public boolean isBirthdayToday(){
        return LocalDate.now().equals(LocalDate.of(this.birthDay.getYearOfBirthday(), this.birthDay.getMonthOfBirthday(), this.birthDay.getDayOfBirthday()));
    }

    public void set(PersonDto personDto){
        if (!StringUtils.isEmpty(personDto.getHobby())){
            this.setHobby(personDto.getHobby());
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
        if(personDto.getBirthday() != null){
            this.setBirthDay(Birthday.of(personDto.getBirthday()));
        }
    }

}
