package com.example.ProTrack.Controller;

import com.example.ProTrack.Exception.ResourceNotFoundException;
import com.example.ProTrack.Model.QCCheckpoint;
import com.example.ProTrack.Repository.QCCheckpointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/qc-checkpoints")
@CrossOrigin(origins = "*")
public class QCCheckpointController {

    @Autowired
    private QCCheckpointRepository checkpointRepository;

    // ✅ Get all checkpoints
    @GetMapping
    public List<QCCheckpoint> getAllCheckpoints() {
        return checkpointRepository.findAll();
    }

    // ✅ Get checkpoint by ID
    @GetMapping("/{id}")
    public QCCheckpoint getCheckpointById(@PathVariable Long id) {
        return checkpointRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QCCheckpoint not found with id: " + id));
    }

    // ✅ Create new checkpoint
    @PostMapping
    public QCCheckpoint createCheckpoint(@RequestBody QCCheckpoint checkpoint) {
        return checkpointRepository.save(checkpoint);
    }

    // ✅ Update checkpoint
    @PutMapping("/{id}")
    public QCCheckpoint updateCheckpoint(@PathVariable Long id, @RequestBody QCCheckpoint checkpointDetails) {
        QCCheckpoint checkpoint = checkpointRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QCCheckpoint not found with id: " + id));

        checkpoint.setName(checkpointDetails.getName());
        checkpoint.setDescription(checkpointDetails.getDescription());
        checkpoint.setRequiredChecks(checkpointDetails.getRequiredChecks());
        checkpoint.setStage(checkpointDetails.getStage());

        return checkpointRepository.save(checkpoint);
    }

    // ✅ Delete checkpoint
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCheckpoint(@PathVariable Long id) {
        QCCheckpoint checkpoint = checkpointRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QCCheckpoint not found with id: " + id));

        checkpointRepository.delete(checkpoint);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ✅ Extra : Get checkpoints by name
    @GetMapping("/name/{name}")
    public List<QCCheckpoint> getCheckpointsByName(@PathVariable String name) {
        return checkpointRepository.findByNameIgnoreCase(name);
    }

    // ✅ Extra : Get checkpoints by production stage
    @GetMapping("/stage/{stageId}")
    public List<QCCheckpoint> getCheckpointsByStage(@PathVariable Long stageId) {
        return checkpointRepository.findByStageId(stageId);
    }
}
