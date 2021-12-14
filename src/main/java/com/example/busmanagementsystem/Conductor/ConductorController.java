package com.example.busmanagementsystem.Conductor;

import com.example.busmanagementsystem.Conductor.Conductor;
import com.example.busmanagementsystem.Conductor.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/conductor")
public class ConductorController {

    private final ConductorService conductorService;

    @Autowired
    public ConductorController(ConductorService conductorService) {
        this.conductorService = conductorService;
    }

    @GetMapping
    public List<Conductor> getConductors () {
        return conductorService.getConductors();
    }

    @PostMapping
    public void registerNewConductor(@RequestBody Conductor conductor) {
        conductorService.addNewConductor(conductor);
    }

    @DeleteMapping(path = "{conductorEmpId}")
    public void deleteConductor(@PathVariable("conductorEmpId") Integer conductorEmpId){
        conductorService.deleteConductorBySid(conductorEmpId);
    }
}
