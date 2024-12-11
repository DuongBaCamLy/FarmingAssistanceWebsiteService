package com.farmbackend.farmbackend.Controller;

import com.farmbackend.farmbackend.DTO.ApiResponse;
import com.farmbackend.farmbackend.DTO.LoginResponse;
import com.farmbackend.farmbackend.Entities.Account;
import com.farmbackend.farmbackend.Entities.Admin;
import com.farmbackend.farmbackend.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable int id) {
        Optional<Admin> admin = adminService.getAdminById(id);
        return admin.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin newAdmin = adminService.createAdmin(admin);
        return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse<Admin>>> login(@RequestBody Account loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Admin account = adminService.login(username, password);
        LoginResponse<Admin> response = new LoginResponse<Admin>();
        response.setAccount(account);
        response.setRole("Admin");
        if (account != null) {
            return ResponseEntity.ok(new ApiResponse(true, "Login successful", response));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(false, "Invalid username or password", response));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable int id, @RequestBody Admin adminDetails) {
        Optional<Admin> updatedAdmin = adminService.updateAdmin(id, adminDetails);
        return updatedAdmin.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable int id) {
        boolean isDeleted = adminService.deleteAdmin(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
