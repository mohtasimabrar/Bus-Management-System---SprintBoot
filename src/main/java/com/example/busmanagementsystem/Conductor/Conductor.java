package com.example.busmanagementsystem.Conductor;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter

@Entity
@Table
public class Conductor {

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
    private Integer empId;
    private String name;
    private  String address;
    private String defaultRoute;
    private Integer salary;

    public Conductor() {
    }

    public Conductor(Integer empId, String name, String address, String defaultRoute, Integer salary) {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.defaultRoute = defaultRoute;
        this.salary = salary;
    }
}
