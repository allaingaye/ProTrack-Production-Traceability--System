package com.example.ProTrack;

import com.example.ProTrack.Model.*;
import com.example.ProTrack.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class ProTrackApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProTrackApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RawMaterialRepository rawMaterialRepository;
	@Autowired
	private ProductionBatchRepository batchRepository;
	@Autowired
	private ProductionTaskRepository taskRepository;
	@Autowired
	private ProductionStageRepository stageRepository;
	@Autowired
	private QCCheckpointRepository checkpointRepository;
	@Autowired
	private QCRecordRepository qcRecordRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private MaterialConsumptionRepository consumptionRepository;

	@Autowired
	private AuditLogRepository auditLogRepository;

	@Override
	public void run(String... args) throws Exception {

//-------- USERS --------
// User admin = new User();
// admin.setFirstName("Alice");
// admin.setLastName("Admin");
// admin.setEmail("lucienallaingaye@gmail.com");
// admin.setPassword("admin123");
// admin.setRole("ADMIN");
// admin.setStatus("ACTIVE");
// userRepository.save(admin);

// User operator = new User();
// operator.setFirstName("Bob");
// operator.setLastName("Operator");
// operator.setEmail("maditahassan00@gmail.com");
// operator.setPassword("operator123");
// operator.setRole("OPERATOR");
// operator.setStatus("ACTIVE");
// userRepository.save(operator);

// -------- RAW MATERIALS --------
// RawMaterial sugar = new RawMaterial();
// sugar.setName("Sugar");
// sugar.setUnit("kg");
// rawMaterialRepository.save(sugar);

// RawMaterial molasses = new RawMaterial();
// molasses.setName("Molasses");
// molasses.setUnit("L");
// rawMaterialRepository.save(molasses);

// -------- PRODUCTION STAGES --------
// ProductionStage stage1 = new ProductionStage();
// stage1.setName("Mixing");
// stage1.setDescription("Mix raw materials");
// stageRepository.save(stage1);

// ProductionStage stage2 = new ProductionStage();
// stage2.setName("Heating");
// stage2.setDescription("Heat mixture");
// stageRepository.save(stage2);

// -------- PRODUCTION BATCHES --------
// ProductionBatch batch1 = new ProductionBatch();
// batch1.setBatchNumber("BATCH001");
// batch1.setStartDate(LocalDate.now().minusDays(3));
// batch1.setEndDate(LocalDate.now().plusDays(2));
// batch1.setStatus("IN_PROGRESS");
// batch1.setProductType("Sugar");
// batch1.setProductQuantity(5000.0);
// batchRepository.save(batch1);

// ProductionBatch batch2 = new ProductionBatch();
// batch2.setBatchNumber("BATCH002");
// batch2.setStartDate(LocalDate.now().minusDays(1));
// batch2.setEndDate(LocalDate.now().plusDays(4));
// batch2.setStatus("IN_PROGRESS");
// batch2.setProductType("Molasses");
// batch2.setProductQuantity(3000.0);
// batchRepository.save(batch2);

// -------- PRODUCTION TASKS --------
// ProductionTask task1 = new ProductionTask();
// task1.setAssignedTo(operator);
// task1.setProductionBatch(batch1);
// task1.setStage(stage1);
// task1.setStatus("PENDING");
// task1.setStartTime(LocalDateTime.now());
// taskRepository.save(task1);

// ProductionTask task2 = new ProductionTask();
// task2.setAssignedTo(operator);
// task2.setProductionBatch(batch2);
// task2.setStage(stage2);
// task2.setStatus("PENDING");
// task2.setStartTime(LocalDateTime.now());
// taskRepository.save(task2);

// -------- QC CHECKPOINTS --------
// QCCheckpoint checkpoint1 = new QCCheckpoint();
// checkpoint1.setName("Sugar Quality Check");
// checkpoint1.setDescription("Check sugar crystal size and purity");
// checkpoint1.setStage(stage1);
// checkpointRepository.save(checkpoint1);

// QCCheckpoint checkpoint2 = new QCCheckpoint();
// checkpoint2.setName("Molasses Quality Check");
// checkpoint2.setDescription("Check molasses density and color");
// checkpoint2.setStage(stage2);
// checkpointRepository.save(checkpoint2);

// -------- QC RECORDS --------
// QCRecord qc1 = new QCRecord();
// qc1.setCheckpoint(checkpoint1);
// qc1.setProductionBatch(batch1);
// qc1.setCheckedBy(admin);
// qc1.setStatus("PASS");
// qc1.setRemarks("Good quality");
// qc1.setDateChecked(LocalDate.now());
// qcRecordRepository.save(qc1);

// QCRecord qc2 = new QCRecord();
// qc2.setCheckpoint(checkpoint2);
// qc2.setProductionBatch(batch2);
// qc2.setCheckedBy(admin);
// qc2.setStatus("FAIL");
// qc2.setRemarks("Needs adjustment");
// qc2.setDateChecked(LocalDate.now());
// qcRecordRepository.save(qc2);

// -------- PRODUCTS --------
// Product product1 = new Product();
// product1.setProductionBatch(batch1);
// product1.setName("Refined Sugar");
// product1.setStatus("READY");
// product1.setQrCode("QR001");
// product1.setBarcode("BAR001");
// productRepository.save(product1);

// Product product2 = new Product();
// product2.setProductionBatch(batch2);
// product2.setName("Molasses Syrup");
// product2.setStatus("READY");
// product2.setQrCode("QR002");
// product2.setBarcode("BAR002");
// productRepository.save(product2);

// -------- MATERIAL CONSUMPTION --------
// MaterialConsumption consumption1 = new MaterialConsumption();
// consumption1.setRawMaterial(sugar);
// consumption1.setProductionBatch(batch1);
// consumptionRepository.save(consumption1);

// MaterialConsumption consumption2 = new MaterialConsumption();
// consumption2.setRawMaterial(molasses);
// consumption2.setProductionBatch(batch2);
// consumptionRepository.save(consumption2);

// -------- AUDIT LOGS --------
// AuditLog log1 = new AuditLog();
// log1.setUser(admin);
// log1.setAction("CREATE");
// log1.setEntityName("ProductionBatch");
// log1.setEntityId(batch1.getId());
// log1.setTimestamp(LocalDateTime.now());
// log1.setDescription("Created first production batch");
// auditLogRepository.save(log1);

// AuditLog log2 = new AuditLog();
// log2.setUser(operator);
// log2.setAction("UPDATE");
// log2.setEntityName("ProductionTask");
// log2.setEntityId(task2.getId());
// log2.setTimestamp(LocalDateTime.now());
// log2.setDescription("Updated task status to pending");
// auditLogRepository.save(log2);

// System.out.println("✅ Sample data loaded successfully!");

	}
}

