package com.example.busmanagementsystem.Tickets;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter

@Entity
@Table
public class Ticket {

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
    private String serialNumber;
    private  String time;
    private  String date;
    private String route;
    private Integer price;
    private boolean isPaid;

    public Ticket() {
    }

    public Ticket(String serialNumber, String time, String date, String route, Integer price, boolean isPaid) {
        this.serialNumber = serialNumber;
        this.time = time;
        this.date = date;
        this.route = route;
        this.price = price;
        this.isPaid = isPaid;
    }
}
