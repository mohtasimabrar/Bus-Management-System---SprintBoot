package com.example.busmanagementsystem.Conductor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConductorService {
    private final ConductorRepository conductorRepository;

    @Autowired
    public ConductorService(ConductorRepository conductorRepository) {
        this.conductorRepository = conductorRepository;
    }

    public List<Conductor> getConductors() {
        return conductorRepository.findAll();
    }

    public void addNewConductor(Conductor conductor) {
        Optional<Conductor> optionalConductor =  conductorRepository.findConductorByEmpID(conductor.getEmpId());
        if (optionalConductor.isPresent()) {
            throw new IllegalStateException("Account Already Exists. Please Try Signing In.");
        } else {
            conductorRepository.save(conductor);
        }
    }

    public Conductor findConductorByEmail(String email) {
        return conductorRepository.findConductorByEmail(email);
    }

    public void deleteConductorBySid(Integer conductorId) {
        Optional<Conductor> optionalConductor =  conductorRepository.findConductorByEmpID(conductorId);
        if (optionalConductor.isPresent()) {
            conductorRepository.deleteById(optionalConductor.get().getId());
        } else {
            throw new IllegalStateException("Conductor with ID "+conductorId+" does not exist!");
        }
    }

    public long conductorCount(){return conductorRepository.count();}
}
