package com.example.ProTrack.Controller;

import com.example.ProTrack.Exception.ResourceNotFoundException;
import com.example.ProTrack.Model.MaterialConsumption;
import com.example.ProTrack.Repository.MaterialConsumptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/material-consumptions")
public class MaterialConsumptionController {

    @Autowired
    private MaterialConsumptionRepository consumptionRepository;

    @GetMapping
    public List<MaterialConsumption> getAllConsumptions() {
        return consumptionRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<MaterialConsumption> createConsumption(@RequestBody MaterialConsumption consumption) {
        MaterialConsumption saved = consumptionRepository.save(consumption);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialConsumption> getConsumptionById(@PathVariable Long id) {
        MaterialConsumption consumption = consumptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consumption not found with id: " + id));
        return ResponseEntity.ok(consumption);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialConsumption> updateConsumption(@PathVariable Long id, @RequestBody MaterialConsumption details) {
        MaterialConsumption consumption = consumptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consumption not found with id: " + id));

        consumption.setRawMaterial(details.getRawMaterial());
        consumption.setProductionBatch(details.getProductionBatch());
        consumption.setQuantity(details.getQuantity());
        consumption.setDateConsumed(details.getDateConsumed());

        MaterialConsumption updated = consumptionRepository.save(consumption);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteConsumption(@PathVariable Long id) {
        MaterialConsumption consumption = consumptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consumption not found with id: " + id));

        consumptionRepository.delete(consumption);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
