package com.example.ProTrack.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String qrCode;
    private String barcode;
    private String status; // READY, DISPATCHED

    @ManyToOne
    @JoinColumn(name = "production_batch_id")
    @JsonIgnoreProperties("products")
    private ProductionBatch productionBatch;
    @Transient
    public Long getProductionBatchId() {
        return productionBatch != null ? productionBatch.getId() : null;
    }


    // Getters and Setters

    public Product() {
    }

    public Product(Long id, String name, String qrCode, String barcode, String status, ProductionBatch productionBatch) {
        this.id = id;
        this.name = name;
        this.qrCode = qrCode;
        this.barcode = barcode;
        this.status = status;
        this.productionBatch = productionBatch;
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

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProductionBatch getProductionBatch() {
        return productionBatch;
    }

    public void setProductionBatch(ProductionBatch productionBatch) {
        this.productionBatch = productionBatch;
    }
}
