package com.example.busmanagementsystem.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student mariam = new Student(
                    1L,
                    "Marium",
                    "Dhanmondi",
                    "CSE",
                    "Dhan_BRAC",
                    200
            );
            Student alex = new Student(
                    2L,
                    "Alex",
                    "Uttara",
                    "EEE",
                    "Utta_BRAC",
                    200
            );

            repository.saveAll(List.of(mariam, alex));
        };
    }
}
