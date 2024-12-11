package com.farmbackend.farmbackend.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmbackend.farmbackend.DTO.FieldSoilRequest;
import com.farmbackend.farmbackend.DTO.FieldSoilResponse;
import com.farmbackend.farmbackend.Entities.Field;
import com.farmbackend.farmbackend.Service.FieldService;

@RestController
@RequestMapping("/api/fields")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<Field>> getAllFieldsByFarmerId( @PathVariable int farmerId) {
    List<Field> fields =fieldService.getAllFieldsByFarmerId(farmerId); 
    return ResponseEntity.ok(fields);
    }

    @GetMapping("/{fieldId}")
    public ResponseEntity<FieldSoilResponse> getFieldSoilDetailById( @PathVariable int fieldId) {
        FieldSoilResponse fieldSoil = fieldService.getFieldSoilDetail(fieldId); 
    return ResponseEntity.ok(fieldSoil);
    }

    @PostMapping("/farmer/{farmerId}")
    public ResponseEntity<Field> createField(@RequestBody FieldSoilRequest params, @PathVariable int farmerId) {
        Field savedField = fieldService.createFieldSoil(params, farmerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedField);
    }   

    // Get fields by location
    // @GetMapping("/location/{location}")
    // public Optional<Field> getFieldsByLocation(@PathVariable String location) {
    //     return fieldService.getFieldsByLocation(location);
    // }

    // Delete a field by ID
    @DeleteMapping("/{id}")
    public void deleteField(@PathVariable int id) {
        fieldService.deleteField(id);
    }

    // Get fields by name
    // @GetMapping("/name/{fieldName}")
    // public Optional<Field> getFieldsByName(@PathVariable String fieldName) {
    //     return fieldService.getFieldsByName(fieldName);
    // }
 
    @PutMapping("/{fieldId}")
    public Field updateField(@PathVariable int fieldId, @RequestBody FieldSoilRequest params) {
            return fieldService.updateField(fieldId, params);
    }
}