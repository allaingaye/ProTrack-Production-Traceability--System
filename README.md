# ProTrack – Production Process Automation and Product Traceability System

##  Introduction
ProTrack is an integrated software solution designed to automate production workflows and enhance the traceability of products within manufacturing environments. Developed for the **Compagnie Sucrière du Tchad (CST)**, the system supports process optimization, real‑time monitoring, and quality assurance by providing end‑to‑end tracking from raw material intake to final product dispatch.



##  General Objective
To develop a digital system that automates production operations and tracks product movement throughout the supply chain, ensuring quality, transparency, and efficiency.



##  Specific Objectives
- Automate task scheduling and process logging within the production line  
- Track raw materials from entry through each processing stage to final product output  
- Assign and trace batch numbers and QR/barcodes for traceability  
- Generate real‑time performance and process efficiency reports  
- Provide quality control checkpoints with data entry and feedback  
- Support audit trails for compliance and operational visibility  



##  Scope
**Included:**
- Digital dashboards for production workflow visualization  
- Inventory and raw material tracking  
- Batch identification and traceability tools (QR/barcode support)  
- Production reporting and analytics module  
- User roles for Admin, operators and  quality controllers  

**Excluded:**
- Hardware integration with physical machines (e.g., sensors, IoT devices)  
- Direct automation of physical machinery  



##  Key Features
- **Production Workflow Module** – Digitally maps each stage of production and assigns task roles  
- **Inventory & Input Tracking** – Logs material quantities, sources, and consumption rates  
- **Traceability Engine** – Assigns traceable IDs (batch numbers/QR codes) for each production unit  
- **Quality Control Portal** – QC officers record inspections, issues, and pass/fail criteria at checkpoints  
- **Reporting & Analytics Dashboard** – Tracks production efficiency, downtime, and bottlenecks  
- **Audit Logs & Data Exports** – All activities recorded with timestamps for traceability and compliance  



## Expected Outcomes
- **Optimized Production Efficiency** – Automation reduces manual errors and bottlenecks, improving overall output  
- **Enhanced Product Traceability** – Complete visibility from raw materials to final product improves quality assurance and accountability  
- **Better Compliance & Reporting** – Accurate records support audits, certifications, and industry standards  
- **Data‑Driven Decision Making** – Management can assess performance and plan improvements based on real‑time data  
- **Scalable System** – Adaptable to other production line or expandable for warehouse integration  



##  Tech Stack
**Backend**
- Java (Spring Boot, Hibernate ORM)  
- PostgreSQL (pgAdmin for management)  
- REST API integration
- OTP verification for security
- Hibernate ORM


##  Role-Based Access Control
ProTrack implements RBAC to secure system modules and enforce accountability.

- Admin: Manage users, workflows, and reports  
- Operator: Execute production tasks and log materials  
- Quality Controller: Perform inspections and record QC data
- 
**Authorization Flow:**
1. User signs in with OTP verification  
2. JWT token issued with embedded role claims  
3. Spring Security middleware validates role before granting access



**Frontend**
- HTML5, CSS3, Vanilla JavaScript  
- Responsive Design (Flexbox, Media Queries)  
- Fetch API for async requests  
- LocalStorage for client‑side persistence  
- DOM Manipulation & Event Listeners  
- CSS Animations  
- Boxicons for icons 
- Google Fonts for typography  

**Tools**
- Git & GitHub for version control  
- IntelliJ IDEA for backend development  
- pgAdmin for database management


  ##  Getting Started
1. Clone the repository
`bash
 git clone https://github.com/allaingaye/ProTrack-Production-Traceability--System.git
3. Configure PostgreSQL database in `application.properties`  
4. Run backend with Spring Boot (`mvn spring-boot:run`)  
5. Open `[index.html](http://localhost:1010/index.html)` in browser for frontend  

.
