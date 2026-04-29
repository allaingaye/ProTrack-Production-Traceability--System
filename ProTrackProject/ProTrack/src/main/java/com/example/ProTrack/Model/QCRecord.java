package com.example.ProTrack.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "qc_records")
public class QCRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "checkpoint_id")
    @JsonIgnoreProperties("record")
    private QCCheckpoint checkpoint;

    @ManyToOne
    @JoinColumn(name = "production_batch_id")
    @JsonIgnoreProperties("record")
    private ProductionBatch productionBatch;

    @ManyToOne
    @JoinColumn(name = "checked_by")
    @JsonIgnoreProperties("record")
    private User checkedBy;

    private String status; // PASS, FAIL
    private String remarks;
    private LocalDate dateChecked;

    // Getters and Setters

    public QCRecord() {
    }

    public QCRecord(Long id, QCCheckpoint checkpoint, ProductionBatch productionBatch, User checkedBy, String status, String remarks, LocalDate dateChecked) {
        this.id = id;
        this.checkpoint = checkpoint;
        this.productionBatch = productionBatch;
        this.checkedBy = checkedBy;
        this.status = status;
        this.remarks = remarks;
        this.dateChecked = dateChecked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QCCheckpoint getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(QCCheckpoint checkpoint) {
        this.checkpoint = checkpoint;
    }

    public ProductionBatch getProductionBatch() {
        return productionBatch;
    }

    public void setProductionBatch(ProductionBatch productionBatch) {
        this.productionBatch = productionBatch;
    }

    public User getCheckedBy() {
        return checkedBy;
    }

    public void setCheckedBy(User checkedBy) {
        this.checkedBy = checkedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDate getDateChecked() {
        return dateChecked;
    }

    public void setDateChecked(LocalDate dateChecked) {
        this.dateChecked = dateChecked;
    }
}
