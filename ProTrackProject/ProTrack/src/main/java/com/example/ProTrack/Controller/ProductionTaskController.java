package com.example.ProTrack.Controller;

import com.example.ProTrack.Exception.ResourceNotFoundException;
import com.example.ProTrack.Model.*;
import com.example.ProTrack.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/tasks")
@CrossOrigin(origins = "*")
public class ProductionTaskController {

    @Autowired
    private ProductionTaskRepository taskRepository;

    @Autowired
    private ProductionBatchRepository batchRepository;

    @Autowired
    private ProductionStageRepository stageRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ Get all tasks
    @GetMapping
    public ResponseEntity<List<ProductionTask>> getAllTasks() {
        try {
            List<ProductionTask> tasks = taskRepository.findAll();
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ✅ Get task by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductionTask> getTaskById(@PathVariable Long id) {
        try {
            ProductionTask task = taskRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("ProductionTask not found with id: " + id));
            return ResponseEntity.ok(task);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // ✅ Create new task - Version SIMPLE
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Map<String, Object> requestData) {
        try {
            System.out.println("Received create task request: " + requestData);

            // Validate required fields
            if (!requestData.containsKey("productionBatchId") ||
                    !requestData.containsKey("stageId") ||
                    !requestData.containsKey("assignedToId") ||
                    !requestData.containsKey("status")) {
                return ResponseEntity.badRequest().body("Missing required fields: productionBatchId, stageId, assignedToId, status");
            }

            // Parse IDs
            Long batchId = Long.parseLong(requestData.get("productionBatchId").toString());
            Long stageId = Long.parseLong(requestData.get("stageId").toString());
            Long assignedToId = Long.parseLong(requestData.get("assignedToId").toString());
            String status = requestData.get("status").toString();

            // Get entities from database
            ProductionBatch batch = batchRepository.findById(batchId)
                    .orElseThrow(() -> new ResourceNotFoundException("Batch not found with id: " + batchId));

            ProductionStage stage = stageRepository.findById(stageId)
                    .orElseThrow(() -> new ResourceNotFoundException("Stage not found with id: " + stageId));

            User user = userRepository.findById(assignedToId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + assignedToId));

            // Create new task
            ProductionTask task = new ProductionTask();
            task.setProductionBatch(batch);
            task.setStage(stage);
            task.setAssignedTo(user);
            task.setStatus(status);

            // Handle optional dates
            if (requestData.containsKey("startTime") && requestData.get("startTime") != null) {
                try {
                    task.setStartTime(LocalDateTime.parse(requestData.get("startTime").toString()));
                } catch (Exception e) {
                    System.out.println("Error parsing startTime: " + e.getMessage());
                }
            }

            if (requestData.containsKey("endTime") && requestData.get("endTime") != null) {
                try {
                    task.setEndTime(LocalDateTime.parse(requestData.get("endTime").toString()));
                } catch (Exception e) {
                    System.out.println("Error parsing endTime: " + e.getMessage());
                }
            }

            ProductionTask savedTask = taskRepository.save(task);
            return new ResponseEntity<>(savedTask, HttpStatus.CREATED);

        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid ID format: " + e.getMessage());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating task: " + e.getMessage());
        }
    }

    // ✅ Update task - Version SIMPLE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody Map<String, Object> requestData) {
        try {
            System.out.println("Updating task ID: " + id + " with data: " + requestData);

            // Get existing task
            ProductionTask task = taskRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

            // Update batch if provided
            if (requestData.containsKey("productionBatchId") && requestData.get("productionBatchId") != null) {
                Long batchId = Long.parseLong(requestData.get("productionBatchId").toString());
                ProductionBatch batch = batchRepository.findById(batchId)
                        .orElseThrow(() -> new ResourceNotFoundException("Batch not found with id: " + batchId));
                task.setProductionBatch(batch);
            }

            // Update stage if provided
            if (requestData.containsKey("stageId") && requestData.get("stageId") != null) {
                Long stageId = Long.parseLong(requestData.get("stageId").toString());
                ProductionStage stage = stageRepository.findById(stageId)
                        .orElseThrow(() -> new ResourceNotFoundException("Stage not found with id: " + stageId));
                task.setStage(stage);
            }

            // Update user if provided
            if (requestData.containsKey("assignedToId") && requestData.get("assignedToId") != null) {
                Long assignedToId = Long.parseLong(requestData.get("assignedToId").toString());
                User user = userRepository.findById(assignedToId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + assignedToId));
                task.setAssignedTo(user);
            }

            // Update status if provided
            if (requestData.containsKey("status") && requestData.get("status") != null) {
                task.setStatus(requestData.get("status").toString());
            }

            // Update start time if provided
            if (requestData.containsKey("startTime") && requestData.get("startTime") != null) {
                try {
                    task.setStartTime(LocalDateTime.parse(requestData.get("startTime").toString()));
                } catch (Exception e) {
                    System.out.println("Error parsing startTime: " + e.getMessage());
                }
            }

            // Update end time if provided
            if (requestData.containsKey("endTime") && requestData.get("endTime") != null) {
                try {
                    task.setEndTime(LocalDateTime.parse(requestData.get("endTime").toString()));
                } catch (Exception e) {
                    System.out.println("Error parsing endTime: " + e.getMessage());
                }
            }

            ProductionTask updatedTask = taskRepository.save(task);
            return ResponseEntity.ok(updatedTask);

        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid ID format: " + e.getMessage());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating task: " + e.getMessage());
        }
    }

    // ✅ Delete task
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        try {
            if (!taskRepository.existsById(id)) {
                throw new ResourceNotFoundException("ProductionTask not found with id: " + id);
            }

            taskRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting task: " + e.getMessage());
        }
    }

    // ✅ Get tasks by status
    @GetMapping("/status/{status}")
    public ResponseEntity<?> getTasksByStatus(@PathVariable String status) {
        try {
            List<ProductionTask> tasks = taskRepository.findByStatusIgnoreCase(status);
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching tasks by status: " + e.getMessage());
        }
    }

    // ✅ Get tasks by assigned user
    @GetMapping("/assigned/{userId}")
    public ResponseEntity<?> getTasksByAssignedUser(@PathVariable Long userId) {
        try {
            List<ProductionTask> tasks = taskRepository.findByAssignedToId(userId);
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching tasks by user: " + e.getMessage());
        }
    }

    // ✅ Get tasks by production batch
    @GetMapping("/batch/{batchId}")
    public ResponseEntity<?> getTasksByBatch(@PathVariable Long batchId) {
        try {
            List<ProductionTask> tasks = taskRepository.findByProductionBatchId(batchId);
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching tasks by batch: " + e.getMessage());
        }
    }
}