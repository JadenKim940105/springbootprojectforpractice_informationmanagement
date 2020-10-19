package com.jaden.springboot.bootprj.common;

import org.springframework.stereotype.Component;

@Component
public class Common {

    public  RuntimeException noIdException(){
        return new RuntimeException("아이디가 존재하지 않습니다.");
    }
}
