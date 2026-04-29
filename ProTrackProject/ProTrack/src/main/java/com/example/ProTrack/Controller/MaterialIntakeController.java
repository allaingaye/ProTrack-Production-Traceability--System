package com.example.ProTrack.Controller;

import com.example.ProTrack.Exception.ResourceNotFoundException;
import com.example.ProTrack.Model.MaterialIntake;
import com.example.ProTrack.Repository.MaterialIntakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/material-intakes")
public class MaterialIntakeController {

    @Autowired
    private MaterialIntakeRepository intakeRepository;

    @GetMapping
    public List<MaterialIntake> getAllIntakes() {
        return intakeRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<MaterialIntake> createIntake(@RequestBody MaterialIntake intake) {
        MaterialIntake saved = intakeRepository.save(intake);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialIntake> getIntakeById(@PathVariable Long id) {
        MaterialIntake intake = intakeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Intake not found with id: " + id));
        return ResponseEntity.ok(intake);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialIntake> updateIntake(@PathVariable Long id, @RequestBody MaterialIntake details) {
        MaterialIntake intake = intakeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Intake not found with id: " + id));

        intake.setRawMaterial(details.getRawMaterial());
        intake.setQuantity(details.getQuantity());
        intake.setSupplier(details.getSupplier());
        intake.setDateReceived(details.getDateReceived());
        intake.setBatchNumber(details.getBatchNumber());

        MaterialIntake updated = intakeRepository.save(intake);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteIntake(@PathVariable Long id) {
        MaterialIntake intake = intakeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Intake not found with id: " + id));

        intakeRepository.delete(intake);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
