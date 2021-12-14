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
        Optional<Student> optionalStudent =  studentRepository.findStudentBySID(student.getSid());
        if (optionalStudent.isPresent()) {
            throw new IllegalStateException("Account Already Exists. Please Try Signing In.");
        } else {
            studentRepository.save(student);
        }
    }

    public void deleteStudentBySid(Integer studentId) {
        Optional<Student> optionalStudent =  studentRepository.findStudentBySID(studentId);
        if (optionalStudent.isPresent()) {
            studentRepository.deleteById(optionalStudent.get().getId());
        } else {
            throw new IllegalStateException("Student with ID "+studentId+" does not exist!");
        }
    }
}
