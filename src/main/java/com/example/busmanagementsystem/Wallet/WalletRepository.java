package com.example.busmanagementsystem.Wallet;


import com.example.busmanagementsystem.Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    @Query("SELECT w FROM Wallet w WHERE w.sid = ?1")
    Optional<Wallet> findWalletBySid (Integer sid);
}
