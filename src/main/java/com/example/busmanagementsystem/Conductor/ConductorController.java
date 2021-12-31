package com.example.busmanagementsystem.Conductor;

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



@Controller
public class ConductorController {

    private final ConductorService conductorService;
    private final BusService busService;
    private final TicketService ticketService;
    private final StudentService studentService;

    @Autowired
    public ConductorController(ConductorService conductorService, StudentService studentService,BusService busService, TicketService ticketService) {
        this.busService = busService;
        this.conductorService = conductorService;
        this.ticketService = ticketService;
        this.studentService = studentService;

    }


    private final String getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return currentUserName;
        }
        return "hello";
    }

    @GetMapping("/admin/conductor")
    public String showAllConductor(Model model) {
        // create model attribute to bind form data
        model.addAttribute("conductor", conductorService.getConductors());
        model.addAttribute("userName", getCurrentAuthenticatedUser());
        return "Admin/admin_conductor";
    }

    @GetMapping("/admin/addConductor/{id}")
    public String showAddConductor(Model model, @PathVariable Long id) {
        // create model attribute to bind form data
        Conductor conductor = new Conductor();
        model.addAttribute("user_id", id);
        model.addAttribute("conductor", conductor);
        return "Admin/admin_add_conductor";
    }

    @PostMapping("/admin/saveConductor")
    public String saveBus(@ModelAttribute("conductor") Conductor conductor) {
        // save employee to database
        conductorService.addNewConductor(conductor);
        return "redirect:/admin/conductor";
    }

    @GetMapping("/admin/deleteConductor/{id}")
    public String deleteConductor(@PathVariable (value = "id") Integer id) {

        // call delete employee method
        conductorService.deleteConductorBySid(id);
        return "redirect:/admin/conductor";
    }

    @GetMapping("/conductor")
    public String viewConductorHome (Model model){
        Conductor conductor = conductorService.findConductorByEmail(getCurrentAuthenticatedUser());
        Bus bus = busService.findBusByConductorID(conductor);
        model.addAttribute("ticket", ticketService.findIncompleteTicketsByBus(bus));
        return "/Conductor/home";
    }

    @PostMapping("/conductor/updateBusStatus")
    public String updateBusStatus(@ModelAttribute("bus") Bus bus) {
        Conductor conductor = conductorService.findConductorByEmail(getCurrentAuthenticatedUser());
        Bus originalBus = busService.findBusByConductorID(conductor);

        originalBus.setStatus(bus.getStatus());
        busService.updateBus(originalBus);

        return "redirect:/conductor/manageBus";
    }

    @GetMapping("/conductor/manageBus")
    public String viewManageBusPage (Model model) {

        Conductor conductor = conductorService.findConductorByEmail(getCurrentAuthenticatedUser());
        Bus bus = busService.findBusByConductorID(conductor);
        model.addAttribute("bus", bus);
        model.addAttribute("passengerCount", bus.getCapacity() - bus.getAvailableSeat());

        return "Conductor/bus_manage";

    }

    @GetMapping("/conductor/completeTicket/{id}")
    public String checkStudentTicket(@PathVariable long id){

        Ticket ticket = ticketService.findTicketByID(id);
        ticket.setStatus("Complete");
        ticketService.updateTicket(ticket);
        Bus bus = ticket.getBus();
        bus.setAvailableSeat(bus.getAvailableSeat()+1);
        busService.updateBus(bus);
        Student student = ticket.getStudent();
        student.setHasTicket(false);
        studentService.updateStudent(student);

        return "redirect:/conductor";

    }




}
