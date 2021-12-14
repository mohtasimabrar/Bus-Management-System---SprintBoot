package com.example.busmanagementsystem.Admin;


import com.example.busmanagementsystem.Admin.Admin;
import com.example.busmanagementsystem.Admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    public void addNewAdmin(Admin admin) {
        Optional<Admin> optionalAdmin =  adminRepository.findAdminByEmpID(admin.getEmpId());
        if (optionalAdmin.isPresent()) {
            throw new IllegalStateException("Account Already Exists. Please Try Signing In.");
        } else {
            adminRepository.save(admin);
        }
    }

    public void deleteAdminBySid(Integer adminId) {
        Optional<Admin> optionalAdmin =  adminRepository.findAdminByEmpID(adminId);
        if (optionalAdmin.isPresent()) {
            adminRepository.deleteById(optionalAdmin.get().getId());
        } else {
            throw new IllegalStateException("Admin with ID "+adminId+" does not exist!");
        }
    }
}
