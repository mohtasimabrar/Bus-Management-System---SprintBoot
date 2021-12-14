package com.example.busmanagementsystem.Bus;

import com.example.busmanagementsystem.Bus.Bus;
import com.example.busmanagementsystem.Bus.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "api/v1/bus")
public class BusController {
    private final BusService busService;

    @Autowired
    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping
    public List<Bus> getBuss () {
        return busService.getBuss();
    }

    @PostMapping
    public void registerNewBus(@RequestBody Bus bus) {
        busService.addNewBus(bus);
    }

    @DeleteMapping(path = "{busId}")
    public void deleteBus(@PathVariable("busId") String busId){
        busService.deleteBusBySid(busId);
    }
}
