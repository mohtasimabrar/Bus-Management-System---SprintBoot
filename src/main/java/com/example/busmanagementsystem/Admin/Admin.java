package com.example.busmanagementsystem.Admin;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter

@Entity
@Table
public class Admin {

    @Id
    @SequenceGenerator(
            name = "admin_sequence",
            sequenceName = "admin_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "admin_sequence"
    )
    private long id;
    private Integer empId;
    private String name;
    private String email;

    public Admin() {
    }

    public Admin(Integer empId, String name, String email) {
        this.empId = empId;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", empId=" + empId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
