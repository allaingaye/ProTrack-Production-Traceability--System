package com.example.ProTrack.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "production_batches")
public class ProductionBatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String batchNumber;

    private LocalDate startDate;
    private LocalDate endDate;
    private String status; // IN_PROGRESS, COMPLETED, QC_PENDING
    private Double productQuantity;
    private String productType;

    @OneToMany(mappedBy = "productionBatch")
    @JsonIgnore
    private Set<ProductionTask> tasks;

    @OneToMany(mappedBy = "productionBatch")
    @JsonIgnore
    private Set<MaterialConsumption> consumptions;

    @OneToMany(mappedBy = "productionBatch")
    @JsonIgnore
    private Set<Product> products;

    @OneToMany(mappedBy = "productionBatch")
    @JsonIgnore
    private Set<QCRecord> qcRecords;

    // Getters and Setters

    public ProductionBatch() {
    }

    public ProductionBatch(Long id, String batchNumber, LocalDate startDate, LocalDate endDate, String status, Double productQuantity, String productType, Set<ProductionTask> tasks, Set<MaterialConsumption> consumptions, Set<Product> products, Set<QCRecord> qcRecords) {
        this.id = id;
        this.batchNumber = batchNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.productQuantity = productQuantity;
        this.productType = productType;
        this.tasks = tasks;
        this.consumptions = consumptions;
        this.products = products;
        this.qcRecords = qcRecords;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Double productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Set<ProductionTask> getTasks() {
        return tasks;
    }

    public void setTasks(Set<ProductionTask> tasks) {
        this.tasks = tasks;
    }

    public Set<MaterialConsumption> getConsumptions() {
        return consumptions;
    }

    public void setConsumptions(Set<MaterialConsumption> consumptions) {
        this.consumptions = consumptions;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<QCRecord> getQcRecords() {
        return qcRecords;
    }

    public void setQcRecords(Set<QCRecord> qcRecords) {
        this.qcRecords = qcRecords;
    }
}
