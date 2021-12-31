package com.example.busmanagementsystem.Tickets;

import com.example.busmanagementsystem.Bus.Bus;
import com.example.busmanagementsystem.Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT t FROM Ticket t WHERE t.id = ?1")
    Optional<Ticket> findTicketByID (long id);

    List<Ticket> findTicketByStudent(Student student);

    @Query("SELECT t FROM Ticket t WHERE t.status = 'Pending' AND t.bus = ?1")
    List<Ticket> findIncompleteTicketsByBus(Bus bus);
}
