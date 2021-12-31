package com.example.busmanagementsystem.Conductor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping(path = "api/v1/conductor")
@Controller
public class ConductorController {

    private final ConductorService conductorService;

    @Autowired
    public ConductorController(ConductorService conductorService) {
        this.conductorService = conductorService;
    }

//    @GetMapping
//    public List<Conductor> getConductors () {
//        return conductorService.getConductors();
//    }
//
//    @PostMapping
//    public void registerNewConductor(@RequestBody Conductor conductor) {
//        conductorService.addNewConductor(conductor);
//    }
//
//    @DeleteMapping(path = "{conductorEmpId}")
//    public void deleteConductor(@PathVariable("conductorEmpId") Integer conductorEmpId){
//        conductorService.deleteConductorBySid(conductorEmpId);
//    }

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
        return "/Conductor/home";
    }
}
