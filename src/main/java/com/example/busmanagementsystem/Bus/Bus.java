package com.example.busmanagementsystem.Bus;

import com.example.busmanagementsystem.Conductor.Conductor;
import com.example.busmanagementsystem.Tickets.Ticket;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @Column(unique = true)
    private String regNumber;
    private String name;
    private String defaultRoute;
    private Integer capacity;
    private String driver;
    private Integer availableSeat;
    private String status;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private Set<Ticket> tickets = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "conductor_id")
    private Conductor conductor;

    public Bus() {
    }

    public Bus(String regNumber, String name, String defaultRoute, Integer capacity, String driver, Integer availableSeat, String status) {
        this.regNumber = regNumber;
        this.name = name;
        this.defaultRoute = defaultRoute;
        this.capacity = capacity;
        this.driver = driver;
        this.availableSeat = availableSeat;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", regNumber='" + regNumber + '\'' +
                ", name='" + name + '\'' +
                ", defaultRoute='" + defaultRoute + '\'' +
                ", capacity=" + capacity +
                ", driver='" + driver + '\'' +
                ", availableSeat=" + availableSeat +
                ", status='" + status + '\'' +
                '}';
    }
}
