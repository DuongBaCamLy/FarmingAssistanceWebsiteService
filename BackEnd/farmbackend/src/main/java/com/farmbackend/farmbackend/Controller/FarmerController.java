package com.farmbackend.farmbackend.Controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmbackend.farmbackend.DTO.ApiResponse;
import com.farmbackend.farmbackend.DTO.LoginResponse;
import com.farmbackend.farmbackend.Entities.Account;
import com.farmbackend.farmbackend.Entities.Farmer;
import com.farmbackend.farmbackend.Service.FarmerService;
@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    private final FarmerService farmerService;

    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    @PostMapping
    public ResponseEntity<Farmer> createFarmer(@RequestBody Farmer farmer) {
        return ResponseEntity.ok(farmerService.createFarmer(farmer));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse<Farmer>>> login(@RequestBody Account loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        
        Farmer account = farmerService.login(username, password);
        LoginResponse<Farmer> response = new LoginResponse<Farmer>();
        response.setAccount(account);
        response.setRole("Farmer");

        if (account != null) {
            return ResponseEntity.ok(new ApiResponse(true, "Login successful", response));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(false, "Invalid username or password", response));
        }
    }

    @GetMapping
    public ResponseEntity<List<Farmer>> getAllFarmers() {
        return ResponseEntity.ok(farmerService.getAllFarmers());
    }

    @GetMapping("/{fieldId}")
    public ResponseEntity<Farmer> getFarmerById(@PathVariable int fieldId) {
        return ResponseEntity.ok(farmerService.getFarmerById(fieldId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Farmer> updateFarmer(@PathVariable int id, @RequestBody Farmer farmer) {
        return ResponseEntity.ok(farmerService.updateFarmer(id, farmer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarmer(@PathVariable int id) {
        farmerService.deleteFarmer(id);
        return ResponseEntity.noContent().build();
    }
}