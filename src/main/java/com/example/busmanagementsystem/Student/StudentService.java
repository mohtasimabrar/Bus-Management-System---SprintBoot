package com.example.busmanagementsystem.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> optionalStudent =  studentRepository.findStudentByID(student.getId());
        if (optionalStudent.isPresent()) {
            throw new IllegalStateException("Account Already Exists. Please Try Signing In.");
        }
        studentRepository.save(student);
    }
}
