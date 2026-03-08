# System Architecture

## Overview

The Hospital Management System follows a **layered architecture** commonly used in enterprise Spring Boot applications.

```
Controller → Service → Repository → Database
```

This architecture ensures **separation of concerns**, maintainability, and scalability.

---

## Layer Breakdown

### Controller Layer

Responsible for handling HTTP requests and responses.

Responsibilities:

* Expose REST API endpoints
* Validate incoming request DTOs
* Return standardized API responses
* Delegate business logic to services

Example controllers:

* PatientController
* DoctorController
* DepartmentController
* AppointmentController
* BillController
* InsuranceController

---

### Service Layer

Contains the **core business logic** of the application.

Responsibilities:

* Validate business rules
* Manage transactions
* Coordinate between repositories
* Throw domain-specific exceptions

Examples:

* AppointmentService
* DoctorService
* PatientService
* BillService

---

### Repository Layer

Responsible for **database interactions** using Spring Data JPA.

Responsibilities:

* CRUD operations
* Query execution
* Data persistence

Example repositories:

* PatientRepository
* DoctorRepository
* AppointmentRepository
* DepartmentRepository
* BillRepository
* InsuranceRepository

---

### Entity Layer

Represents the **database tables** and relationships.

Uses **JPA annotations** such as:

* @Entity
* @Table
* @ManyToOne
* @OneToMany
* @OneToOne
* @ManyToMany

---

## DTO Pattern

The system uses **DTOs (Data Transfer Objects)** to separate internal entity models from API responses.

```
Entity → Mapper → Response DTO
Request DTO → Mapper → Entity
```

Benefits:

* Prevents exposing internal entity structure
* Allows validation before persistence
* Improves API clarity

---

## Exception Handling

The project uses a **GlobalExceptionHandler** with `@RestControllerAdvice`.

Handled exceptions include:

* ResourceNotFoundException
* DuplicateResourceException
* BusinessException
* Validation exceptions

This ensures **consistent API error responses**.

---

## Transaction Management

Critical service operations use:

```
@Transactional
```

Benefits:

* Atomic operations
* Automatic rollback on failure
* Data consistency
