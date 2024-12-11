package com.farmbackend.farmbackend.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmbackend.farmbackend.Entities.Field;
import com.farmbackend.farmbackend.Entities.Soil;
import com.farmbackend.farmbackend.Repository.FieldRepository;
import com.farmbackend.farmbackend.Repository.SoilRepository;

@Service
public class SoilService {

    @Autowired
    private SoilRepository soilRepository;

    @Autowired
    private FieldRepository fieldRepository;

    // Save or update soil
    public Soil saveSoil(Soil soil) {
        return soilRepository.save(soil);
    }

    // Fetch all soils
    public List<Soil> getAllSoils() {
        return soilRepository.findAll();
    }

    // Fetch soil by ID
    public Optional<Soil> getSoilById(int soilId) {
        return soilRepository.findById(soilId);
    }

    // Update an existing soil record
    public Soil updateSoil(int id, Soil updatedSoil) {
        Optional<Soil> existingSoilOpt = soilRepository.findById(id);

        if (existingSoilOpt.isPresent()) {
            Soil existingSoil = existingSoilOpt.get();
            // existingSoil.setSoilTemperature(updatedSoil.getSoilTemperature());
            existingSoil.setpHLevel(updatedSoil.getpHLevel());
            // existingSoil.setMoistureLevel(updatedSoil.getMoistureLevel());
            // existingSoil.setRecordAt(updatedSoil.getRecordAt());
            existingSoil.setField(updatedSoil.getField());
            return soilRepository.save(existingSoil);
        } else {
            throw new RuntimeException("Soil with ID " + id + " not found");
        }
    }

    // Delete soil by ID
    public void deleteSoil(int soilId) {
        soilRepository.deleteById(soilId);
    }

    public Soil getSoilByFieldId(int fieldId) {
        return soilRepository.findByField_FieldId(fieldId);
    }
}
