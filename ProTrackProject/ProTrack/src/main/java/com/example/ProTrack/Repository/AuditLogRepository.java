package com.example.ProTrack.Repository;

import com.example.ProTrack.Model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    // Rechercher les logs par utilisateur
    List<AuditLog> findByUserId(Long userId);

    // Rechercher les logs par nom d'entité
    List<AuditLog> findByEntityNameIgnoreCase(String entityName);
}