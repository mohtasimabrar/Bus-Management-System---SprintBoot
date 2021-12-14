package com.example.busmanagementsystem.Bus;

import com.example.busmanagementsystem.Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    @Query("SELECT b FROM Bus b WHERE b.regNumber = ?1")
    Optional<Bus> findBusByRegNumber (String regNumber);
}
