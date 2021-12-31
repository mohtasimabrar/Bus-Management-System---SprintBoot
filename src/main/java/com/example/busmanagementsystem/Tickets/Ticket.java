package com.example.busmanagementsystem.Tickets;

import com.example.busmanagementsystem.Bus.Bus;
import com.example.busmanagementsystem.Student.Student;
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
            name = "ticket_sequence",
            sequenceName = "ticket_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ticket_sequence"
    )
    private long id;
    private  String time;
    private  String date;
    private Integer price;
    private Integer isPaid;
    private String status;

    @ManyToOne
    @JoinColumn(name = "bus_id", referencedColumnName = "id")
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    public Ticket() {
    }

    public Ticket(String time, String date, Integer price, Integer isPaid, String status) {
        this.time = time;
        this.date = date;
        this.price = price;
        this.isPaid = isPaid;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", isPaid=" + isPaid +
                ", status='" + status + '\'' +
                '}';
    }
}
