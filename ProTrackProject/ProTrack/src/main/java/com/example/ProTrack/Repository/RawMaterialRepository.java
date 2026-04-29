package com.example.ProTrack.Repository;

import com.example.ProTrack.Model.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
    List<RawMaterial> findByNameIgnoreCase(String name);
    List<RawMaterial> findByCurrentQuantityLessThan(Double threshold);
}
