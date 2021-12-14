package com.example.busmanagementsystem.Wallet;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table
public class Wallet {

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
    private Integer balance;

    public Wallet() {
    }

    public Wallet(Integer sid, Integer balance) {
        this.sid = sid;
        this.balance = balance;
    }
}
