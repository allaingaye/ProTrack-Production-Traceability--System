package com.example.ProTrack.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "material_intakes")
public class MaterialIntake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "raw_material_id")
    @JsonIgnoreProperties("intakes") // Alternative approach
    private RawMaterial rawMaterial;

    private Double quantity;
    private String supplier;
    private LocalDate dateReceived;
    private String batchNumber;

    // Getters and Setters

    public MaterialIntake() {
    }

    public MaterialIntake(Long id, RawMaterial rawMaterial, Double quantity, String supplier, LocalDate dateReceived, String batchNumber) {
        this.id = id;
        this.rawMaterial = rawMaterial;
        this.quantity = quantity;
        this.supplier = supplier;
        this.dateReceived = dateReceived;
        this.batchNumber = batchNumber;
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

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public LocalDate getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(LocalDate dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }
}
