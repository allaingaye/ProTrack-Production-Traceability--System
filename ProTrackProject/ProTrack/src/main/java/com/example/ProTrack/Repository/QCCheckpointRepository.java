package com.example.ProTrack.Repository;

import com.example.ProTrack.Model.QCCheckpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QCCheckpointRepository extends JpaRepository<QCCheckpoint, Long> {

    // Rechercher les checkpoints par nom
    List<QCCheckpoint> findByNameIgnoreCase(String name);

    // Rechercher les checkpoints par stage
    List<QCCheckpoint> findByStageId(Long stageId);
}
