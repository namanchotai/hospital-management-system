package com.naman.hms.repository;

import com.naman.hms.entity.Bill;
import com.naman.hms.enums.BillStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Long> {

    Optional<Bill> findByAppointmentId(Long appointmentId);

    List<Bill> findByStatus(BillStatus status);

}