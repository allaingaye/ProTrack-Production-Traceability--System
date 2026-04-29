package com.example.ProTrack.Controller;

import com.example.ProTrack.Exception.ResourceNotFoundException;
import com.example.ProTrack.Model.QCRecord;
import com.example.ProTrack.Repository.QCRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/qc-records")
@CrossOrigin(origins = "*")
public class QCRecordController {

    @Autowired
    private QCRecordRepository qcRecordRepository;

    // ✅ Get all QC records
    @GetMapping
    public List<QCRecord> getAllQCRecords() {
        return qcRecordRepository.findAll();
    }

    // ✅ Get QC record by ID
    @GetMapping("/{id}")
    public QCRecord getQCRecordById(@PathVariable Long id) {
        return qcRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QCRecord not found with id: " + id));
    }

    // ✅ Create new QC record
    @PostMapping
    public QCRecord createQCRecord(@RequestBody QCRecord qcRecord) {
        return qcRecordRepository.save(qcRecord);
    }

    // ✅ Update QC record
    @PutMapping("/{id}")
    public QCRecord updateQCRecord(@PathVariable Long id, @RequestBody QCRecord qcDetails) {
        QCRecord qc = qcRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QCRecord not found with id: " + id));

        qc.setCheckpoint(qcDetails.getCheckpoint());
        qc.setProductionBatch(qcDetails.getProductionBatch());
        qc.setCheckedBy(qcDetails.getCheckedBy());
        qc.setStatus(qcDetails.getStatus());
        qc.setRemarks(qcDetails.getRemarks());
        qc.setDateChecked(qcDetails.getDateChecked());

        return qcRecordRepository.save(qc);
    }

    // ✅ Delete QC record
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteQCRecord(@PathVariable Long id) {
        QCRecord qc = qcRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QCRecord not found with id: " + id));

        qcRecordRepository.delete(qc);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ✅ Extra : Get QC records by status
    @GetMapping("/status/{status}")
    public List<QCRecord> getQCRecordsByStatus(@PathVariable String status) {
        return qcRecordRepository.findByStatusIgnoreCase(status);
    }

    // ✅ Extra : Get QC records by checkpoint
    @GetMapping("/checkpoint/{checkpointId}")
    public List<QCRecord> getQCRecordsByCheckpoint(@PathVariable Long checkpointId) {
        return qcRecordRepository.findByCheckpointId(checkpointId);
    }

    // ✅ Extra : Get QC records by batch
    @GetMapping("/batch/{batchId}")
    public List<QCRecord> getQCRecordsByBatch(@PathVariable Long batchId) {
        return qcRecordRepository.findByProductionBatchId(batchId);
    }

    // ✅ Extra : Get QC records by user
    @GetMapping("/user/{userId}")
    public List<QCRecord> getQCRecordsByUser(@PathVariable Long userId) {
        return qcRecordRepository.findByCheckedById(userId);
    }
}
