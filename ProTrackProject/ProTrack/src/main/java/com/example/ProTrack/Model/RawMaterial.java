package com.example.ProTrack.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "raw_materials")
public class RawMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String unit;
    private Double currentQuantity;
    private Double minimumThreshold;

    @OneToMany(mappedBy = "rawMaterial")
    @JsonIgnore
    private Set<MaterialIntake> intakes;

    @OneToMany(mappedBy = "rawMaterial")
    @JsonIgnore
    private Set<MaterialConsumption> consumptions;

    // Getters and Setters


    public RawMaterial() {
    }

    public RawMaterial(Long id, String name, String description, String unit, Double currentQuantity, Double minimumThreshold, Set<MaterialIntake> intakes, Set<MaterialConsumption> consumptions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.currentQuantity = currentQuantity;
        this.minimumThreshold = minimumThreshold;
        this.intakes = intakes;
        this.consumptions = consumptions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(Double currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public Double getMinimumThreshold() {
        return minimumThreshold;
    }

    public void setMinimumThreshold(Double minimumThreshold) {
        this.minimumThreshold = minimumThreshold;
    }

    public Set<MaterialIntake> getIntakes() {
        return intakes;
    }

    public void setIntakes(Set<MaterialIntake> intakes) {
        this.intakes = intakes;
    }

    public Set<MaterialConsumption> getConsumptions() {
        return consumptions;
    }

    public void setConsumptions(Set<MaterialConsumption> consumptions) {
        this.consumptions = consumptions;
    }
}
