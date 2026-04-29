package com.example.ProTrack.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;
    private String status;
    private String role;

    @OneToMany(mappedBy = "assignedTo")
    @JsonIgnore // Ignore lors de la sérialisation pour éviter les boucles
    private Set<ProductionTask> tasks;

    @OneToMany(mappedBy = "checkedBy")
    @JsonIgnore
    private Set<QCRecord> qcRecords;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<AuditLog> auditLogs;

    // Getters et Setters

    public User() {
    }

    public User(Long id, String firstName, String lastName, String email, String password, String status, String role, Set<ProductionTask> tasks, Set<QCRecord> qcRecords, Set<AuditLog> auditLogs) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.role = role;
        this.tasks = tasks;
        this.qcRecords = qcRecords;
        this.auditLogs = auditLogs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<ProductionTask> getTasks() {
        return tasks;
    }

    public void setTasks(Set<ProductionTask> tasks) {
        this.tasks = tasks;
    }

    public Set<QCRecord> getQcRecords() {
        return qcRecords;
    }

    public void setQcRecords(Set<QCRecord> qcRecords) {
        this.qcRecords = qcRecords;
    }

    public Set<AuditLog> getAuditLogs() {
        return auditLogs;
    }

    public void setAuditLogs(Set<AuditLog> auditLogs) {
        this.auditLogs = auditLogs;
    }
}
