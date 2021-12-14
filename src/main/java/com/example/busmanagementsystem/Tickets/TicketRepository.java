package com.example.busmanagementsystem.Tickets;

import com.example.busmanagementsystem.Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT t FROM Ticket t WHERE t.serialNumber = ?1")
    Optional<Ticket> findTicketBySerial (String serialNumber);
}
