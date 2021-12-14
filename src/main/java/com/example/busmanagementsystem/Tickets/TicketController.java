package com.example.busmanagementsystem.Tickets;

import com.example.busmanagementsystem.Tickets.Ticket;
import com.example.busmanagementsystem.Tickets.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ticket")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getTickets () {
        return ticketService.getTickets();
    }

    @PostMapping
    public void registerNewTicket(@RequestBody Ticket ticket) {
        ticketService.addNewTicket(ticket);
    }

    @DeleteMapping(path = "{ticketSerial}")
    public void deleteTicket(@PathVariable("ticketSerial") String ticketSerial){
        ticketService.deleteTicketBySerial(ticketSerial);
    }
}
