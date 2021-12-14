package com.example.busmanagementsystem.Conductor;


import com.example.busmanagementsystem.Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConductorRepository extends JpaRepository<Conductor, Long> {
    @Query("SELECT c FROM Conductor c WHERE c.empId = ?1")
    Optional<Conductor> findConductorByEmpID (Integer empId);
}
