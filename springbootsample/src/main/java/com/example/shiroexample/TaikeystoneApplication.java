package com.example.shiroexample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.shiroexample.dao")
public class TaikeystoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaikeystoneApplication.class, args);
    }

}
