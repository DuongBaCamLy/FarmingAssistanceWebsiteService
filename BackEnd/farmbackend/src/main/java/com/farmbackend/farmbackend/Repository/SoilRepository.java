package com.farmbackend.farmbackend.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmbackend.farmbackend.Entities.Field;
import com.farmbackend.farmbackend.Entities.Soil;

@Repository
public interface SoilRepository extends JpaRepository<Soil, Integer> {
    Soil findByField_FieldId(int fieldId); // Custom query to fetch Soil by Field ID
}
