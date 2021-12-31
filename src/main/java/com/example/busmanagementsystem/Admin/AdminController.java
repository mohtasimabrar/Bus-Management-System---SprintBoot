package com.example.busmanagementsystem.Admin;


import com.example.busmanagementsystem.Bus.BusService;
import com.example.busmanagementsystem.Conductor.ConductorService;
import com.example.busmanagementsystem.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping(path = "api/v1/admin")
@Controller
public class AdminController {

    private final AdminService adminService;
    private final BusService busService;
    private final ConductorService conductorService;
    private final UserService userService;

    @Autowired
    public AdminController(AdminService adminService, BusService busService, ConductorService conductorService, UserService userService) {
        this.adminService = adminService;
        this.busService = busService;
        this.conductorService = conductorService;
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String viewAdminHome(Model model) {
        model.addAttribute("adminCount", adminService.adminCount());
        model.addAttribute("busCount", busService.busCount());
        model.addAttribute("conductorCount", conductorService.conductorCount());
        return "Admin/home";
    }

    @GetMapping("/admin/administrator")
    public String showAllAdmins(Model model) {
        // create model attribute to bind form data
        model.addAttribute("administrator", adminService.getAdmins());
        return "Admin/admin_administrator";
    }

    @GetMapping("/admin/addAdministrator/{id}")
    public String showAddAdmin(Model model, @PathVariable Long id) {
        // create model attribute to bind form data
        Admin administrator = new Admin();
        model.addAttribute("user_id", id);
        model.addAttribute("administrator", administrator);
        return "Admin/admin_add_administrator";
    }

    @PostMapping("/admin/saveAdministrator")
    public String saveAdmin(@ModelAttribute("administrator") Admin administrator) {
        // save employee to database
        adminService.addNewAdmin(administrator);
        return "redirect:/admin/administrator";
    }

    @GetMapping("/admin/deleteAdministrator/{id}")
    public String deleteAdministrator(@PathVariable (value = "id") Integer id) {

        // call delete employee method
        adminService.deleteAdminBySid(id);
        return "redirect:/admin/administrator";
    }
}
