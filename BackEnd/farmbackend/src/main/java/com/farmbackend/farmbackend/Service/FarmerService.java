package com.farmbackend.farmbackend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmbackend.farmbackend.Entities.Account;
import com.farmbackend.farmbackend.Entities.Admin;
import com.farmbackend.farmbackend.Entities.Farmer;
import com.farmbackend.farmbackend.Repository.FarmerRepository;

@Service
public class FarmerService {

    @Autowired
    private FarmerRepository farmerRepository;

    public FarmerService(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    public Farmer createFarmer(Farmer farmer) {
        return this.farmerRepository.save(farmer);
    }

    public List<Farmer> getAllFarmers() {
        return this.farmerRepository.findAll();
    }

    public Farmer getFarmerById(int id) {
        return farmerRepository.findById(id).orElseThrow(() -> new RuntimeException("Farmer not found"));
    }

    public Farmer updateFarmer(int id, Farmer updatedFarmer) {
        Farmer existingFarmer = getFarmerById(id);
        // existingFarmer.setEmail(updatedFarmer.getEmail());
        // existingFarmer.setProfileStatus(updatedFarmer.getProfileStatus());
        // existingFarmer.setFirstName(updatedFarmer.getFirstName());
        // existingFarmer.setLastName(updatedFarmer.getLastName());
        // existingFarmer.setFarmLocation(updatedFarmer.getFarmLocation());
        // existingFarmer.setPhoneNumber(updatedFarmer.getPhoneNumber());
        return farmerRepository.save(existingFarmer);
    }

    public void deleteFarmer(int id) {
        farmerRepository.deleteById(id);
    }

     public Farmer login(String username, String password) {
        Optional<Farmer> result = farmerRepository.findByAccountUsername(username);

        if (result.isPresent() && result.get().getAccount().getPassword().equals(password)) {
            return result.get();
        }

        return null;
    }
}