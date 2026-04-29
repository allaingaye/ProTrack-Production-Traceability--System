package com.example.ProTrack.Repository;

import com.example.ProTrack.Model.ProductionStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionStageRepository extends JpaRepository<ProductionStage, Long> {
}
