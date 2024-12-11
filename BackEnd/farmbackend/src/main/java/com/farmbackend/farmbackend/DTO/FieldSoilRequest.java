package com.farmbackend.farmbackend.DTO;

import com.farmbackend.farmbackend.Entities.Field;
import com.farmbackend.farmbackend.Entities.Soil;

public class FieldSoilRequest {
    private Field field;
    private Soil soil;

    public Soil getSoil() {
        return soil;
    }

    public void setSoil(Soil soil) {
        this.soil = soil;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
    
}
