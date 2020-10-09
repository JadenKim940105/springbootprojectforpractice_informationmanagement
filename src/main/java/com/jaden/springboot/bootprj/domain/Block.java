package com.jaden.springboot.bootprj.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data // getter, setter, EqualsAndHashCode, toString, RequiredArgsConstructor
public class Block {
    @Id
    @GeneratedValue
    private long id;
    @NonNull
    private String name;

    private String reason;
    private LocalDate startDate;
    private LocalDate endDate;


}
