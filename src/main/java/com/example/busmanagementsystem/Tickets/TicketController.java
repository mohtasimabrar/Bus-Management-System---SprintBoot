package com.example.busmanagementsystem.Tickets;

import com.example.busmanagementsystem.Bus.Bus;
import com.example.busmanagementsystem.Bus.BusService;
import com.example.busmanagementsystem.Student.Student;
import com.example.busmanagementsystem.Student.StudentService;
import com.example.busmanagementsystem.Tickets.Ticket;
import com.example.busmanagementsystem.Tickets.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class TicketController {

    private final TicketService ticketService;
    private final StudentService studentService;
    private final BusService busService;

    @Autowired
    public TicketController(TicketService ticketService, StudentService studentService, BusService busService) {
        this.ticketService = ticketService;
        this.studentService = studentService;
        this.busService = busService;
    }

    private final String getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return currentUserName;
        }
        return null;
    }

    @GetMapping
    public List<Ticket> getTickets () {
        return ticketService.getTickets();
    }

    @PostMapping
    public void registerNewTicket(@RequestBody Ticket ticket) {
        ticketService.addNewTicket(ticket);
    }


    @GetMapping("/student/buyTicket")
    public String showBuyTicket(Model model) {
        Student student = studentService.findUserByID(getCurrentAuthenticatedUser());
        model.addAttribute("student", student);
        Ticket ticket = new Ticket();
        model.addAttribute("ticket", ticket);
        model.addAttribute("availableBus", busService.getBuss());
        return "Student/buy_ticket";
    }

    @PostMapping("/student/confirmTicket")
    public String confirmPurchase(@ModelAttribute("bus") Ticket ticket){
        Student student = studentService.findUserByID(getCurrentAuthenticatedUser());
        if (student.isHasTicket()){
            throw new IllegalStateException("User already has a Ticket. Complete the ride first");
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        ticket.setDate(dtf.format(now));
        ticketService.addNewTicket(ticket);
        Bus bus = busService.findBusByID(ticket.getBus().getId());
        bus.setAvailableSeat(bus.getAvailableSeat()-1);
        busService.updateBus(bus);
        student.setHasTicket(true);
        if (ticket.getIsPaid().equals(1)){
            student.setBalance(student.getBalance()-50);
        }
        studentService.updateStudent(student);
        return "redirect:/student/profile";
    }

    @GetMapping("/student/profile")
    public String studentProfile(Model model) {
        Student student = studentService.findUserByID(getCurrentAuthenticatedUser());
        model.addAttribute("student", student);
        model.addAttribute("ticket", ticketService.findTicketByStudent(student));
        return "Student/student_profile";
    }

    @GetMapping("/student/cancelTicket/{id}")
    public String cencelTicket(@PathVariable (value = "id") long id) {
        Student student = studentService.findUserByID(getCurrentAuthenticatedUser());
        Ticket ticket = ticketService.findTicketByID(id);
        Bus bus = ticket.getBus();
        if (ticket.getIsPaid().equals(1)){
            student.setBalance(student.getBalance()+50);
        }
        ticketService.deleteTicketByID(id);
        bus.setAvailableSeat(bus.getAvailableSeat()+1);
        busService.updateBus(bus);
        student.setHasTicket(false);
        studentService.updateStudent(student);


        return "redirect:/student/profile";
    }

}
