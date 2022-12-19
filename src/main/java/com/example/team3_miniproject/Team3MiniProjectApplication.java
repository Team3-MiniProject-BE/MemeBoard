package com.example.team3_miniproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Team3MiniProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Team3MiniProjectApplication.class, args);
    }
}
