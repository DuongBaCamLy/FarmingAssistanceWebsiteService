package com.farmbackend.farmbackend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmbackend.farmbackend.DTO.FieldSoilRequest;
import com.farmbackend.farmbackend.DTO.FieldSoilResponse;
import com.farmbackend.farmbackend.Entities.Farmer;
import com.farmbackend.farmbackend.Entities.Field;
import com.farmbackend.farmbackend.Entities.Soil;
import com.farmbackend.farmbackend.Repository.FarmerRepository;
import com.farmbackend.farmbackend.Repository.FieldRepository;
import com.farmbackend.farmbackend.Repository.SoilRepository;

import jakarta.transaction.Transactional;

@Service
public class FieldService {

    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    private FarmerRepository farmerRepository;
    @Autowired
    private SoilRepository soilRepository;

    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    public Field getFieldById(int fieldId) {
        return fieldRepository.findById(fieldId).orElse(null);
    }

    @Transactional
    public Field createField(Field field, int farmerId) {
        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new IllegalArgumentException("Farmer not found"));

        field.setFarmer(farmer);

        return fieldRepository.save(field);
    }

    public void deleteField(int fieldId) {
        fieldRepository.deleteById(fieldId);
    }

    @Transactional
    public Field createFieldSoil(FieldSoilRequest params, int farmerId) {
        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new IllegalArgumentException("Farmer not found"));

        Field field = params.getField();
        Soil soil = params.getSoil();

        field.setFarmer(farmer);

        fieldRepository.save(field);

        soil.setField(field);
        soilRepository.save(soil);

        return field;
    }

    @Transactional
    public Field updateField(int fieldId, FieldSoilRequest params) {
        // Tìm Field hiện tại từ cơ sở dữ liệu
        Field existingField = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new IllegalArgumentException("Field not found"));

// Cập nhật thông tin Field
        Field updatedField = params.getField();
        existingField.setName(updatedField.getName());
        existingField.setStatus(updatedField.getStatus());

        Soil existingSoil = soilRepository.findByField_FieldId(fieldId);

        if (params.getSoil() != null) {
            Soil updatedSoil = params.getSoil();
            if (existingSoil == null) {
                Soil newSoil = new Soil();
                newSoil.setField(existingField);
                newSoil.setpHLevel(updatedSoil.getpHLevel());
                newSoil.setTemperature(updatedSoil.getTemperature());
                soilRepository.save(newSoil);
            } else {
                existingSoil.setpHLevel(updatedSoil.getpHLevel());
                existingSoil.setTemperature(updatedSoil.getTemperature());
                soilRepository.save(existingSoil);
            }
        }

// Lưu Field đã cập nhật
        return fieldRepository.save(existingField);
    }

    public List<Field> getAllFieldsByFarmerId(int farmerId) {
        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new IllegalArgumentException("Farmer not found with ID: " + farmerId));

        return fieldRepository.findByFarmer(farmer);
    }

    public FieldSoilResponse getFieldSoilDetail(int fieldId) {
        FieldSoilResponse response = new FieldSoilResponse();
        Optional<Field> field = fieldRepository.findById(fieldId);
        Soil soil = soilRepository.findByField_FieldId(fieldId);
        response.setField(field.get());
        response.setSoil(soil);
        return response;
    }
}
