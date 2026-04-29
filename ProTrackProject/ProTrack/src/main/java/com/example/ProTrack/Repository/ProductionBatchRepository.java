package com.example.ProTrack.Repository;


import com.example.ProTrack.Model.ProductionBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionBatchRepository extends JpaRepository<ProductionBatch, Long> {

    // Recherche par numéro de lot unique
    ProductionBatch findByBatchNumber(String batchNumber);

    // Filtrer par statut (IN_PROGRESS, COMPLETED, QC_PENDING)
    List<ProductionBatch> findByStatusIgnoreCase(String status);
}
