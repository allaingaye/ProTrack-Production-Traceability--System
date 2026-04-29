package com.example.ProTrack.Repository;

import com.example.ProTrack.Model.QCRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QCRecordRepository extends JpaRepository<QCRecord, Long> {

    // Rechercher par statut PASS / FAIL
    List<QCRecord> findByStatusIgnoreCase(String status);

    // Rechercher par checkpoint
    List<QCRecord> findByCheckpointId(Long checkpointId);

    // Rechercher par production batch
    List<QCRecord> findByProductionBatchId(Long batchId);

    // Rechercher par utilisateur ayant effectué le contrôle
    List<QCRecord> findByCheckedById(Long userId);
}
