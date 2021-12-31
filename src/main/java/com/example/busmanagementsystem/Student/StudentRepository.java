package com.example.busmanagementsystem.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.sid = ?1")
    Optional<Student> findStudentBySID (Integer sid);

    @Query("SELECT s FROM Student s WHERE s.user.email = ?1")
    Optional<Student> findStudentByID (String email);

}
