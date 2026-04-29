package com.example.ProTrack.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "production_stages")
public class ProductionStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Integer stageOrder;

    @OneToMany(mappedBy = "stage")
    @JsonIgnore
    private Set<ProductionTask> tasks;

    @OneToMany(mappedBy = "stage")
    @JsonIgnore
    private Set<QCCheckpoint> checkpoints;

    // Getters and Setters

    public ProductionStage() {
    }

    public ProductionStage(Long id, String name, String description, Integer stageOrder, Set<ProductionTask> tasks, Set<QCCheckpoint> checkpoints) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stageOrder = stageOrder;
        this.tasks = tasks;
        this.checkpoints = checkpoints;
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

    public Integer getStageOrder() {
        return stageOrder;
    }

    public void setStageOrder(Integer stageOrder) {
        this.stageOrder = stageOrder;
    }

    public Set<ProductionTask> getTasks() {
        return tasks;
    }

    public void setTasks(Set<ProductionTask> tasks) {
        this.tasks = tasks;
    }

    public Set<QCCheckpoint> getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(Set<QCCheckpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }
}
