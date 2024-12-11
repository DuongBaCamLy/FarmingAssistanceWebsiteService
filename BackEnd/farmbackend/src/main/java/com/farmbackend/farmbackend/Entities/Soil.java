package com.farmbackend.farmbackend.Entities;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Soil")
public class Soil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "soil_id")
    private int soilId;

    @OneToOne
    @JoinColumn(name = "field_id", nullable = false)
    private Field field;

    @Column(name = "pH_level")
    private BigDecimal  pHLevel;

    private BigDecimal temperature;

    // Getters and Setters

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public BigDecimal  getpHLevel() {
        return pHLevel;
    }

    public void setpHLevel(BigDecimal  pHLevel) {
        this.pHLevel = pHLevel;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public int getSoilId() {
        return soilId;
    }

    public void setSoilId(int soilId) {
        this.soilId = soilId;
    }
}