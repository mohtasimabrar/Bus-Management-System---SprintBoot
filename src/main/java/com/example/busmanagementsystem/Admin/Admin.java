package com.example.busmanagementsystem.Admin;

import com.example.busmanagementsystem.User.User;
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
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(unique = true)
    private Integer empId;
    private String designation;

    public Admin() {
    }

    public Admin(long id, Integer empId, String designation) {
        this.id = id;
        this.empId = empId;
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", empId=" + empId +
                ", designation='" + designation + '\'' +
                '}';
    }
}