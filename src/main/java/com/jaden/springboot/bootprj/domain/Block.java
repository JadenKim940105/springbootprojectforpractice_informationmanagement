package com.jaden.springboot.bootprj.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data // getter, setter, EqualsAndHashCode, toString, RequiredArgsConstructor
public class Block {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String reason;
    private LocalDate startDate;
    private LocalDate endDate;


}
