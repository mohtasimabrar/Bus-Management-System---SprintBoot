package com.example.busmanagementsystem.User;

import com.example.busmanagementsystem.Bus.BusService;
import com.example.busmanagementsystem.Student.Student;
import com.example.busmanagementsystem.Student.StudentService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;
    private PasswordEncoder passwordEncoder;
    private final StudentService studentService;
    private final BusService busService;

    public UserController (UserService userService, PasswordEncoder passwordEncoder, StudentService studentService, BusService busService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.studentService = studentService;
        this.busService = busService;
    }

    private final String getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return currentUserName;
        }
        return "hello";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String home( Model model ){
        User user = userService.findUserByEmail(getCurrentAuthenticatedUser());
        if (user.getRole().equals("ROLE_ADMIN")){
            return "redirect:/admin";
        } else if (user.getRole().equals("ROLE_CONDUCTOR")){
            return "redirect:/conductor";
        } else {
            Student student = studentService.findUserByID(getCurrentAuthenticatedUser());
            model.addAttribute("student", student);
            model.addAttribute("bus", busService.getBuss());
            return "Student/index";
        }
    }

    @GetMapping("/admin/addAdminUser")
    public String showAddAdminUser(Model model) {
        // create model attribute to bind form data
        User user = new User();
        model.addAttribute("user", user);
        return "Admin/admin_add_admin_user";
    }

    @PostMapping("/admin/saveAdminUser")
    public String saveAdminUser(@ModelAttribute("user") User user) {
        // save employee to database
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addNewUser(user);
        return  "redirect:/admin/addAdministrator/"+user.getId();
    }

    @GetMapping("/admin/addConductorUser")
    public String showAddConductorUser(Model model) {
        // create model attribute to bind form data
        User user = new User();
        model.addAttribute("user", user);
        return "Admin/admin_add_conductor_user";
    }

    @PostMapping("/admin/saveConductorUser")
    public String saveConductorUser(@ModelAttribute("user") User user) {
        // save employee to database
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addNewUser(user);
        return  "redirect:/admin/addConductor/"+user.getId();
    }

    @GetMapping("/register/addStudentUser")
    public String showAddStudentUser(Model model) {
        // create model attribute to bind form data
        User user = new User();
        model.addAttribute("user", user);
        return "Student/add_student_user";
    }

    @PostMapping("/register/saveStudentUser")
    public String saveStudentUser(@ModelAttribute("user") User user) {
        // save employee to database
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addNewUser(user);
        return  "redirect:/register/addStudent/"+user.getId();
    }
}
