package com.naman.hms.repository;

import com.naman.hms.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

    Optional<Insurance> findByPolicyNumber(String policyNumber);

    boolean existsByPolicyNumber(String policyNumber);

    List<Insurance> findByValidUntilAfter(LocalDate date);

}