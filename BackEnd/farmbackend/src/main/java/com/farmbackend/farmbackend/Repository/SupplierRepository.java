package com.farmbackend.farmbackend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmbackend.farmbackend.Entities.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
        Optional<Supplier> findByAccountUsername(String username);

}
