package com.example.ProTrack.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "production_tasks")
public class ProductionTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stage_id")
    @JsonIgnoreProperties("productions")
    private ProductionStage stage;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    @JsonIgnoreProperties("productions")
    private User assignedTo;

    @ManyToOne
    @JoinColumn(name = "production_batch_id")
    @JsonIgnoreProperties("productions")
    private ProductionBatch productionBatch;

    private String status; // PENDING, IN_PROGRESS, COMPLETED
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // Getters and Setters


    public ProductionTask() {
    }

    public ProductionTask(Long id, ProductionStage stage, User assignedTo, ProductionBatch productionBatch, String status, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.stage = stage;
        this.assignedTo = assignedTo;
        this.productionBatch = productionBatch;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductionStage getStage() {
        return stage;
    }

    public void setStage(ProductionStage stage) {
        this.stage = stage;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public ProductionBatch getProductionBatch() {
        return productionBatch;
    }

    public void setProductionBatch(ProductionBatch productionBatch) {
        this.productionBatch = productionBatch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
