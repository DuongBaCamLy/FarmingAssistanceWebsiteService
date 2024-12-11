package com.farmbackend.farmbackend.Controller;

import java.util.List;
import java.util.Optional; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmbackend.farmbackend.Entities.Soil;
import com.farmbackend.farmbackend.Service.SoilService;

@RestController
@RequestMapping("/api/soils")
public class SoilController {

    @Autowired
    private SoilService soilService;

    // Create a new soil record
    @PostMapping
    public Soil createSoil(@RequestBody Soil soil) {
        return soilService.saveSoil(soil);
    }

    // Get a soil record by ID
    @GetMapping("/{id}")
    public Optional<Soil> getSoilById(@PathVariable int id) {
        return soilService.getSoilById(id);
    }

    // Get all soil records
    @GetMapping
    public List<Soil> getAllSoils() {
        return soilService.getAllSoils();
    }

    // Update an existing soil record
    @PutMapping("/{id}")
    public Soil updateSoil(@PathVariable int id, @RequestBody Soil updatedSoil) {
        return soilService.updateSoil(id, updatedSoil);
    }

    // Delete a soil record by ID
    @DeleteMapping("/{id}")
    public void deleteSoil(@PathVariable int id) {
        soilService.deleteSoil(id);
    }

    @GetMapping("/field/{fieldId}")
    public Soil getSoilByFieldId(@PathVariable int fieldId) {
        return soilService.getSoilByFieldId(fieldId);
    }
}