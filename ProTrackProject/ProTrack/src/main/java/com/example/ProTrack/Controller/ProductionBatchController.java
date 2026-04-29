package com.example.ProTrack.Controller;

import com.example.ProTrack.Exception.ResourceNotFoundException;
import com.example.ProTrack.Model.ProductionBatch;
import com.example.ProTrack.Repository.ProductionBatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/batches")
@CrossOrigin(origins = "*")
public class ProductionBatchController {

    @Autowired
    private ProductionBatchRepository batchRepository;

    // ✅ Get all batches
    @GetMapping
    public List<ProductionBatch> getAllBatches() {
        return batchRepository.findAll();
    }

    // ✅ Get batch by ID
    @GetMapping("/{id}")
    public ProductionBatch getBatchById(@PathVariable Long id) {
        return batchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProductionBatch not found with id: " + id));
    }

    // ✅ Create new batch
    @PostMapping
    public ProductionBatch createBatch(@RequestBody ProductionBatch batch) {
        return batchRepository.save(batch);
    }

    // ✅ Update batch
    @PutMapping("/{id}")
    public ProductionBatch updateBatch(@PathVariable Long id, @RequestBody ProductionBatch batchDetails) {
        ProductionBatch batch = batchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProductionBatch not found with id: " + id));

        batch.setBatchNumber(batchDetails.getBatchNumber());
        batch.setStartDate(batchDetails.getStartDate());
        batch.setEndDate(batchDetails.getEndDate());
        batch.setStatus(batchDetails.getStatus());
        batch.setProductQuantity(batchDetails.getProductQuantity());
        batch.setProductType(batchDetails.getProductType());

        return batchRepository.save(batch);
    }

    // ✅ Delete batch
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBatch(@PathVariable Long id) {
        ProductionBatch batch = batchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProductionBatch not found with id: " + id));

        batchRepository.delete(batch);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ✅ Extra : Get batch by batch number
    @GetMapping("/number/{batchNumber}")
    public ProductionBatch getBatchByNumber(@PathVariable String batchNumber) {
        return batchRepository.findByBatchNumber(batchNumber);
    }

    // ✅ Extra : Get batches by status
    @GetMapping("/status/{status}")
    public List<ProductionBatch> getBatchesByStatus(@PathVariable String status) {
        return batchRepository.findByStatusIgnoreCase(status);
    }
}
