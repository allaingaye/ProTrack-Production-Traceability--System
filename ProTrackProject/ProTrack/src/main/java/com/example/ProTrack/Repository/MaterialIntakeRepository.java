package com.example.ProTrack.Repository;

import com.example.ProTrack.Model.MaterialIntake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MaterialIntakeRepository extends JpaRepository<MaterialIntake, Long> {
    List<MaterialIntake> findBySupplierIgnoreCase(String supplier);
    List<MaterialIntake> findByDateReceivedBetween(LocalDate start, LocalDate end);
}
