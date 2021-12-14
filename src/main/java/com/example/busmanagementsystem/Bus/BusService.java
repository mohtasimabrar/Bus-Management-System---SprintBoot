package com.example.busmanagementsystem.Bus;

import com.example.busmanagementsystem.Bus.Bus;
import com.example.busmanagementsystem.Bus.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {
    private final BusRepository busRepository;

    @Autowired
    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public List<Bus> getBuss() {
        return busRepository.findAll();
    }

    public void addNewBus(Bus bus) {
        Optional<Bus> optionalBus =  busRepository.findBusByRegNumber(bus.getRegNumber());
        if (optionalBus.isPresent()) {
            throw new IllegalStateException("Account Already Exists. Please Try Signing In.");
        } else {
            busRepository.save(bus);
        }
    }

    public void deleteBusBySid(String busId) {
        Optional<Bus> optionalBus =  busRepository.findBusByRegNumber(busId);
        if (optionalBus.isPresent()) {
            busRepository.deleteById(optionalBus.get().getId());
        } else {
            throw new IllegalStateException("Bus with ID "+busId+" does not exist!");
        }
    }
}
