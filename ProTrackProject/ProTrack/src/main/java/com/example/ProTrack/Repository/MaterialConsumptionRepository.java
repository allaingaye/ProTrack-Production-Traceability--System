package com.example.ProTrack.Repository;

import com.example.ProTrack.Model.MaterialConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MaterialConsumptionRepository extends JpaRepository<MaterialConsumption, Long> {
    List<MaterialConsumption> findByDateConsumed(LocalDate date);
}
