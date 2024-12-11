package com.farmbackend.farmbackend.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmbackend.farmbackend.Entities.Farmer;
import com.farmbackend.farmbackend.Entities.Field;

@Repository
public interface FieldRepository extends JpaRepository<Field, Integer> {
     List<Field> findByFarmer(Farmer farmer);
}