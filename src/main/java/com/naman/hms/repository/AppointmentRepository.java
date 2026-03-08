package com.naman.hms.repository;

import com.naman.hms.entity.Appointment;
import com.naman.hms.enums.AppointmentStatus;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByPatientId(Long id);

    boolean existsByDoctorIdAndAppointmentTimeAndStatusNot(
            Long doctorId,
            LocalDateTime appointmentTime,
            AppointmentStatus status
    );

    List<Appointment> findByDoctorId(Long doctorId);

    List<Appointment> findByPatientId(Long patientId);

    @Query("""
           SELECT a FROM Appointment a
           WHERE a.appointmentTime BETWEEN :start AND :end
           """)
    List<Appointment> findByDateRange(LocalDateTime start, LocalDateTime end);
}