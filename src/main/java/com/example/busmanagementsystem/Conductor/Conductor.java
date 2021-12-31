package com.example.busmanagementsystem.Conductor;


import com.example.busmanagementsystem.Bus.Bus;
import com.example.busmanagementsystem.User.User;
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
            name = "conductor_sequence",
            sequenceName = "conductor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "conductor_sequence"
    )
    private long id;
    @Column(unique = true)
    private Integer empId;
    private  String address;
    private String defaultRoute;
    private Integer salary;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "conductor")
    private Bus bus;


    public Conductor() {
    }

    public Conductor(Integer empId, String address, String defaultRoute, Integer salary) {
        this.empId = empId;
        this.address = address;
        this.defaultRoute = defaultRoute;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Conductor{" +
                "id=" + id +
                ", empId=" + empId +
                ", address='" + address + '\'' +
                ", defaultRoute='" + defaultRoute + '\'' +
                ", salary=" + salary +
                '}';
    }
}
