package com.naman.hms.repository;

import com.naman.hms.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    boolean existsByEmail(String email);
    boolean existsByInsuranceId(Long insuranceId);
}