package com.example.busmanagementsystem.Admin;


import com.example.busmanagementsystem.Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("SELECT a FROM Admin a WHERE a.empId = ?1")
    Optional<Admin> findAdminByEmpID (Integer empId);
}
