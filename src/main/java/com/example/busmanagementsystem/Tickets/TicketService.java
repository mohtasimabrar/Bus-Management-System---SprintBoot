package com.example.busmanagementsystem.Tickets;

import com.example.busmanagementsystem.Tickets.Ticket;
import com.example.busmanagementsystem.Tickets.TicketRepository;
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

    public void addNewTicket(Ticket ticket) {
        Optional<Ticket> optionalTicket =  ticketRepository.findTicketBySerial(ticket.getSerialNumber());
        if (optionalTicket.isPresent()) {
            throw new IllegalStateException("Account Already Exists. Please Try Signing In.");
        } else {
            ticketRepository.save(ticket);
        }
    }

    public void deleteTicketBySerial(String ticketId) {
        Optional<Ticket> optionalTicket =  ticketRepository.findTicketBySerial(ticketId);
        if (optionalTicket.isPresent()) {
            ticketRepository.deleteById(optionalTicket.get().getId());
        } else {
            throw new IllegalStateException("Ticket with ID "+ticketId+" does not exist!");
        }
    }
}
