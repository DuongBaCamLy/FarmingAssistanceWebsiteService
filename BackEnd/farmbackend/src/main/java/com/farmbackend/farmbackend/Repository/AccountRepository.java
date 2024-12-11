package com.farmbackend.farmbackend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmbackend.farmbackend.Entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    // Find account by username
    Optional<Account> findByUsername(String username);

    // Find account by email
    Optional<Account> findByEmail(String email);

    // Check if an account exists by username
    boolean existsByUsername(String username);

    // Check if an account exists by email
    boolean existsByEmail(String email);
}