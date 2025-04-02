package com.example.spring_crud_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringCrudAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCrudAppApplication.class, args);
    }
}
