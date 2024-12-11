package com.farmbackend.farmbackend.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmbackend.farmbackend.Entities.Admin;
import com.farmbackend.farmbackend.Entities.Farmer;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Integer> {
    // Find a farmer by last name
    // List<Farmer> findByLastName(String lastName);

    // Find a farmer by email
    // Farmer findByEmail(String email);
    // Find a farmer by phone number
    // Optional<Farmer> findByPhoneNumber(String phoneNumber);
    // Find farmers by profile status
    // List<Farmer> findByProfileStatus(String profileStatus);
    // Find farmers by farm location (case-insensitive)
    // List<Farmer> findByFarmLocationIgnoreCase(String farmLocation);
    // // Find farmers registered after a specific date
    // List<Farmer> findByRegistrationDateAfter(LocalDate registrationDate);
    Optional<Farmer> findByAccountUsername(String username);

}
