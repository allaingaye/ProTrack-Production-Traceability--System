package com.example.ProTrack.Controller;

import com.example.ProTrack.Exception.ResourceNotFoundException;
import com.example.ProTrack.Model.ProductionStage;
import com.example.ProTrack.Repository.ProductionStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/stages")
public class ProductionStageController {

    @Autowired
    private ProductionStageRepository stageRepository;

    @GetMapping
    public List<ProductionStage> getAllStages() {
        return stageRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<ProductionStage> createStage(@RequestBody ProductionStage stage) {
        ProductionStage saved = stageRepository.save(stage);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductionStage> getStageById(@PathVariable Long id) {
        ProductionStage stage = stageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stage not found with id: " + id));
        return ResponseEntity.ok(stage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductionStage> updateStage(@PathVariable Long id, @RequestBody ProductionStage details) {
        ProductionStage stage = stageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stage not found with id: " + id));

        stage.setName(details.getName());
        stage.setDescription(details.getDescription());
        stage.setStageOrder(details.getStageOrder());

        ProductionStage updated = stageRepository.save(stage);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStage(@PathVariable Long id) {
        ProductionStage stage = stageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stage not found with id: " + id));

        stageRepository.delete(stage);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
