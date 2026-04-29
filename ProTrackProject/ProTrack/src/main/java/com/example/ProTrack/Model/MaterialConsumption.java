package com.example.ProTrack.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "material_consumptions")
public class MaterialConsumption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "raw_material_id")
    @JsonIgnoreProperties("consumptions") // Alternative approach
    private RawMaterial rawMaterial;

    @ManyToOne
    @JoinColumn(name = "production_batch_id")
    @JsonIgnoreProperties("consumptions")
    private ProductionBatch productionBatch;

    private Double quantity;
    private LocalDate dateConsumed;

    // Getters and Setters


    public MaterialConsumption() {
    }

    public MaterialConsumption(Long id, RawMaterial rawMaterial, ProductionBatch productionBatch, Double quantity, LocalDate dateConsumed) {
        this.id = id;
        this.rawMaterial = rawMaterial;
        this.productionBatch = productionBatch;
        this.quantity = quantity;
        this.dateConsumed = dateConsumed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public ProductionBatch getProductionBatch() {
        return productionBatch;
    }

    public void setProductionBatch(ProductionBatch productionBatch) {
        this.productionBatch = productionBatch;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDateConsumed() {
        return dateConsumed;
    }

    public void setDateConsumed(LocalDate dateConsumed) {
        this.dateConsumed = dateConsumed;
    }
}
