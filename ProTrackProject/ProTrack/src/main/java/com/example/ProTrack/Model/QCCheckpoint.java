package com.example.ProTrack.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "qc_checkpoints")
public class QCCheckpoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String requiredChecks;

    @ManyToOne
    @JoinColumn(name = "stage_id")
    @JsonIgnoreProperties("points")
    private ProductionStage stage;

    @OneToMany(mappedBy = "checkpoint")
    @JsonIgnore
    private Set<QCRecord> records;

    // Getters and Setters

    public QCCheckpoint() {
    }

    public QCCheckpoint(Long id, String name, String description, String requiredChecks, ProductionStage stage, Set<QCRecord> records) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.requiredChecks = requiredChecks;
        this.stage = stage;
        this.records = records;
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

    public String getRequiredChecks() {
        return requiredChecks;
    }

    public void setRequiredChecks(String requiredChecks) {
        this.requiredChecks = requiredChecks;
    }

    public ProductionStage getStage() {
        return stage;
    }

    public void setStage(ProductionStage stage) {
        this.stage = stage;
    }

    public Set<QCRecord> getRecords() {
        return records;
    }

    public void setRecords(Set<QCRecord> records) {
        this.records = records;
    }
}
