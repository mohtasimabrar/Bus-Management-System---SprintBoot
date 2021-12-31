package com.example.busmanagementsystem.Tickets;

import com.example.busmanagementsystem.Bus.Bus;
import com.example.busmanagementsystem.Student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    public List<Ticket> findTicketByStudent(Student student) {
        List<Ticket> tickets =  ticketRepository.findTicketByStudent(student);
        return tickets;
    }

    public Ticket findTicketByID(long id) {
        Optional<Ticket> optionalTicket =  ticketRepository.findTicketByID(id);
        if (optionalTicket.isPresent()) {
            return optionalTicket.get();
        } else {
            throw new IllegalStateException("Ticket does not exist.");
        }
    }

    public Ticket updateTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void addNewTicket(Ticket ticket) {
        Optional<Ticket> optionalTicket =  ticketRepository.findTicketByID(ticket.getId());
        if (optionalTicket.isPresent()) {
            throw new IllegalStateException("Ticket Already Exists. Please Try A New One.");
        } else {
            ticketRepository.save(ticket);
        }
    }

    public List<Ticket> findIncompleteTicketsByBus(Bus bus) {
        List<Ticket> tickets = ticketRepository.findIncompleteTicketsByBus(bus);
        return tickets;
    }



    public void deleteTicketByID(long ticketId) {
        Optional<Ticket> optionalTicket =  ticketRepository.findTicketByID(ticketId);
        if (optionalTicket.isPresent()) {
            ticketRepository.deleteById(optionalTicket.get().getId());
        } else {
            throw new IllegalStateException("Ticket with ID "+ticketId+" does not exist!");
        }
    }
}
