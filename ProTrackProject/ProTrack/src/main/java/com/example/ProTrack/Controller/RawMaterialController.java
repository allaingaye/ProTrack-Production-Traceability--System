package com.example.ProTrack.Controller;

import com.example.ProTrack.Exception.ResourceNotFoundException;
import com.example.ProTrack.Model.RawMaterial;
import com.example.ProTrack.Repository.RawMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/raw-materials")
public class RawMaterialController {

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    // ✅ Get all raw materials
    @GetMapping
    public List<RawMaterial> getAllRawMaterials() {
        return rawMaterialRepository.findAll();
    }

    // ✅ Create new raw material
    @PostMapping
    public ResponseEntity<RawMaterial> createRawMaterial(@RequestBody RawMaterial rawMaterial) {
        RawMaterial savedMaterial = rawMaterialRepository.save(rawMaterial);
        return new ResponseEntity<>(savedMaterial, HttpStatus.CREATED);
    }

    // ✅ Get raw material by ID
    @GetMapping("/{id}")
    public ResponseEntity<RawMaterial> getRawMaterialById(@PathVariable Long id) {
        RawMaterial material = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RawMaterial not found with id: " + id));
        return ResponseEntity.ok(material);
    }

    // ✅ Update raw material
    @PutMapping("/{id}")
    public ResponseEntity<RawMaterial> updateRawMaterial(
            @PathVariable Long id,
            @RequestBody RawMaterial materialDetails) {

        RawMaterial material = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RawMaterial not found with id: " + id));

        material.setName(materialDetails.getName());
        material.setDescription(materialDetails.getDescription());
        material.setUnit(materialDetails.getUnit());
        material.setCurrentQuantity(materialDetails.getCurrentQuantity());
        material.setMinimumThreshold(materialDetails.getMinimumThreshold());

        RawMaterial updatedMaterial = rawMaterialRepository.save(material);
        return ResponseEntity.ok(updatedMaterial);
    }

    // ✅ Delete raw material
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRawMaterial(@PathVariable Long id) {
        RawMaterial material = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RawMaterial not found with id: " + id));

        rawMaterialRepository.delete(material);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ✅ Extra: Find by name
    @GetMapping("/name/{name}")
    public ResponseEntity<List<RawMaterial>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(rawMaterialRepository.findByNameIgnoreCase(name));
    }

    // ✅ Extra: Find low stock materials
    @GetMapping("/low-stock/{threshold}")
    public ResponseEntity<List<RawMaterial>> getLowStock(@PathVariable Double threshold) {
        return ResponseEntity.ok(rawMaterialRepository.findByCurrentQuantityLessThan(threshold));
    }
}
