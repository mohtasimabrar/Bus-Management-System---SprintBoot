package com.example.busmanagementsystem.Student;

import javax.persistence.*;

import com.example.busmanagementsystem.Tickets.Ticket;
import com.example.busmanagementsystem.User.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@Entity
@Table
public class Student {

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
    private Integer sid;
    private String address;
    private String dept;
    private String defaultRoute;
    private Integer balance;
    private boolean hasTicket;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Ticket> tickets = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Student() {
        this.balance = 0;
        this.hasTicket = false;
    }

    public Student(Integer sid, String address, String dept, String defaultRoute) {
        this.sid = sid;
        this.address = address;
        this.dept = dept;
        this.defaultRoute = defaultRoute;
        this.balance = 0;
        this.hasTicket = false;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", sid=" + sid +
                ", address='" + address + '\'' +
                ", dept='" + dept + '\'' +
                ", defaultRoute='" + defaultRoute + '\'' +
                ", balance=" + balance +
                ", hasTicket=" + hasTicket +
                '}';
    }
}
