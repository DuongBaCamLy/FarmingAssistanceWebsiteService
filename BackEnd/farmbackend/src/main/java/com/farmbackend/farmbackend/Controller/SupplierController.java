package com.farmbackend.farmbackend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmbackend.farmbackend.DTO.ApiResponse;
import com.farmbackend.farmbackend.DTO.LoginResponse;
import com.farmbackend.farmbackend.Entities.Account;
import com.farmbackend.farmbackend.Entities.Supplier;
import com.farmbackend.farmbackend.Service.SupplierService;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
     private SupplierService supplierService;

     @GetMapping
public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody Account loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Supplier account = supplierService.login(username, password);
        LoginResponse<Supplier> response = new LoginResponse<Supplier>();
        response.setAccount(account);
        response.setRole("Supplier");
        if (account != null) {
            return ResponseEntity.ok(new ApiResponse(true, "Login successful", response));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(false, "Invalid username or password", response));
        }
    }
    
}
