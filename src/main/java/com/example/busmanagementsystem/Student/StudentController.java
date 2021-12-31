package com.example.busmanagementsystem.Student;

import com.example.busmanagementsystem.Student.Student;
import com.example.busmanagementsystem.Tickets.TicketController;
import com.example.busmanagementsystem.Tickets.TicketService;
import com.example.busmanagementsystem.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;
    private final TicketService ticketService;

    @Autowired
    public StudentController(StudentService studentService, TicketService ticketService) {
        this.studentService = studentService;
        this.ticketService = ticketService;
    }

    private final String getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return currentUserName;
        }
        return null;
    }

    @GetMapping("/register/addStudent/{id}")
    public String showAddStudent(Model model, @PathVariable Long id) {
        // create model attribute to bind form data
        Student student = new Student();
        model.addAttribute("user_id", id);
        model.addAttribute("student", student);
        return "Student/add_student";
    }

    @PostMapping("/register/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        // save employee to database
        studentService.addNewStudent(student);
        return "redirect:/login";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable (value = "id") Integer id) {

        // call delete employee method
        studentService.deleteStudentBySid(id);
        return "redirect:/students";
    }

    @GetMapping("/student/wallet")
    public String studentWallet(Model model) {
        Student student = studentService.findUserByID(getCurrentAuthenticatedUser());
        model.addAttribute("student", student);
        model.addAttribute("tickets", ticketService.findTicketByStudent(student));

        return "Student/wallet";
    }

    @GetMapping("/student/paymentDone")
    public String studentMoneyAdded(Model model) {
        return "Student/payment_gateway";
    }


    @PostMapping("/student/addMoney")
    public String studentAddMoney(@ModelAttribute("student") Student student) {
        Student currentStudent = studentService.findUserByID(getCurrentAuthenticatedUser());
        currentStudent.setBalance(currentStudent.getBalance() + student.getBalance());
        studentService.updateStudent(currentStudent);

        return "redirect:/student/paymentDone";
    }



}
