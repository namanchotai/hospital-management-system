package com.naman.hms.repository;

import com.naman.hms.entity.Doctor;
import com.naman.hms.enums.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    boolean existsByEmail(String email);

    List<Doctor> getBySpecialization(Specialization specialization);
}