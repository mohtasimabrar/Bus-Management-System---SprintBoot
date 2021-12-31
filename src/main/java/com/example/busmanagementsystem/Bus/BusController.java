package com.example.busmanagementsystem.Bus;

import com.example.busmanagementsystem.Conductor.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping(path = "api/v1/bus")
@Controller
public class BusController {
    private final BusService busService;
    private final ConductorService conductorService;

    @Autowired
    public BusController(BusService busService, ConductorService conductorService) {
        this.busService = busService;
        this.conductorService = conductorService;
    }

    private final String getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return currentUserName;
        }
        return null;
    }

//    @GetMapping
//    public List<Bus> getBuss () {
//        return busService.getBuss();
//    }
//
//    @PostMapping
//    public void registerNewBus(@RequestBody Bus bus) {
//        busService.addNewBus(bus);
//    }
//
//    @DeleteMapping(path = "{busId}")
//    public void deleteBus(@PathVariable("busId") String busId){
//        busService.deleteBusBySid(busId);
//    }
    @GetMapping("/admin/bus")
    public String showAllBus(Model model) {
        // create model attribute to bind form data
        model.addAttribute("bus", busService.getBuss());
        return "Admin/admin_bus";
    }

    @GetMapping("/admin/addBus")
    public String showAddBus(Model model) {
        // create model attribute to bind form data
        Bus bus = new Bus();
        model.addAttribute("bus", bus);
        model.addAttribute("conductor", conductorService.getConductors());
        return "Admin/admin_add_bus";
    }

    @PostMapping("/admin/saveBus")
    public String saveBus(@ModelAttribute("bus") Bus bus) {
        // save employee to database
        busService.addNewBus(bus);
        return "redirect:/admin/bus";
    }

    @GetMapping("/admin/deleteBus/{id}")
    public String deleteBus(@PathVariable (value = "id") String id) {

        // call delete employee method
        busService.deleteBusBySid(id);
        return "redirect:/admin/bus";
    }
}
