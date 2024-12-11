package com.farmbackend.farmbackend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmbackend.farmbackend.Entities.Account;
import com.farmbackend.farmbackend.Entities.Admin;
import com.farmbackend.farmbackend.Repository.AdminRepository;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(int id) {
        return adminRepository.findById(id);
    }

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Optional<Admin> updateAdmin(int id, Admin adminDetails) {
        return adminRepository.findById(id);
        //     .map(admin -> {
        //         admin.setName(adminDetails.getName());
        //         // Cập nhật các thuộc tính khác nếu cần
        //         return adminRepository.save(admin);
        //     });
    }

    public boolean deleteAdmin(int id) {
        return adminRepository.findById(id)
                .map(admin -> {
                    adminRepository.delete(admin);
                    return true;
                })
                .orElse(false);
    }

    public Admin login(String username, String password) {
        Optional<Admin> result = adminRepository.findByAccountUsername(username);

        if (result.isPresent() && result.get().getAccount().getPassword().equals(password)) {
            return result.get();
        }

        return null;
    }
}
