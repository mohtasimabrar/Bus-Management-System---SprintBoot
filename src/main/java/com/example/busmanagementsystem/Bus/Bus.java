package com.example.busmanagementsystem.Bus;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table
public class Bus {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private long id;
    private String regNumber;
    private String name;
    private String defaultRoute;
    private Integer capacity;
    private String driver;
    private Integer availableSeat;

    public Bus() {
    }

    public Bus(String regNumber, String name, String defaultRoute, Integer capacity, String driver, Integer availableSeat) {
        this.regNumber = regNumber;
        this.name = name;
        this.defaultRoute = defaultRoute;
        this.capacity = capacity;
        this.driver = driver;
        this.availableSeat = availableSeat;
    }
}
