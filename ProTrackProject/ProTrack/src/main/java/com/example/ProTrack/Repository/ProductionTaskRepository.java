package com.example.ProTrack.Repository;

import com.example.ProTrack.Model.ProductionTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionTaskRepository extends JpaRepository<ProductionTask, Long> {

    // Rechercher les tâches par statut (PENDING, IN_PROGRESS, COMPLETED)
    List<ProductionTask> findByStatusIgnoreCase(String status);

    // Rechercher les tâches assignées à un utilisateur
    List<ProductionTask> findByAssignedToId(Long userId);

    // Rechercher les tâches d'un batch spécifique
    List<ProductionTask> findByProductionBatchId(Long batchId);
}
