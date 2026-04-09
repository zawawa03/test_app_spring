package com.example.test_app_apring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.test_app_apring.repository")
public class TestAppApringApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestAppApringApplication.class, args);
    }
}
