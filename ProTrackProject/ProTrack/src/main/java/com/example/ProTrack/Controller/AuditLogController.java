package com.example.ProTrack.Controller;

import com.example.ProTrack.Exception.ResourceNotFoundException;
import com.example.ProTrack.Model.AuditLog;
import com.example.ProTrack.Repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/audit-logs")
@CrossOrigin(origins = "*")
public class AuditLogController {

    @Autowired
    private AuditLogRepository auditLogRepository;

    // ✅ Get all audit logs
    @GetMapping
    public List<AuditLog> getAllAuditLogs() {
        return auditLogRepository.findAll();
    }

    // ✅ Get audit log by ID
    @GetMapping("/{id}")
    public AuditLog getAuditLogById(@PathVariable Long id) {
        return auditLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AuditLog not found with id: " + id));
    }

    // ✅ Create new audit log
    @PostMapping
    public AuditLog createAuditLog(@RequestBody AuditLog auditLog) {
        return auditLogRepository.save(auditLog);
    }

    // ✅ Update audit log
    @PutMapping("/{id}")
    public AuditLog updateAuditLog(@PathVariable Long id, @RequestBody AuditLog auditDetails) {
        AuditLog auditLog = auditLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AuditLog not found with id: " + id));

        auditLog.setUser(auditDetails.getUser());
        auditLog.setAction(auditDetails.getAction());
        auditLog.setEntityName(auditDetails.getEntityName());
        auditLog.setEntityId(auditDetails.getEntityId());
        auditLog.setTimestamp(auditDetails.getTimestamp());
        auditLog.setDescription(auditDetails.getDescription());

        return auditLogRepository.save(auditLog);
    }

    // ✅ Delete audit log
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAuditLog(@PathVariable Long id) {
        AuditLog auditLog = auditLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AuditLog not found with id: " + id));

        auditLogRepository.delete(auditLog);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ✅ Extra : Get audit logs by user
    @GetMapping("/user/{userId}")
    public List<AuditLog> getAuditLogsByUser(@PathVariable Long userId) {
        return auditLogRepository.findByUserId(userId);
    }

    // ✅ Extra : Get audit logs by entity name
    @GetMapping("/entity/{entityName}")
    public List<AuditLog> getAuditLogsByEntity(@PathVariable String entityName) {
        return auditLogRepository.findByEntityNameIgnoreCase(entityName);
    }
}
