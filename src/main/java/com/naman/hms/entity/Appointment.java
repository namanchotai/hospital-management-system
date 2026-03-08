package com.naman.hms.entity;

import com.naman.hms.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="appointments")
public class Appointment  extends  BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "patient_id",nullable = false)
    private Patient patient;
    @Column(nullable = false)
    private LocalDateTime appointmentTime;
    @Column(length=500)
    private String reason;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="doctor_id", nullable = false)
    private Doctor doctor;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL,orphanRemoval = true)
    private Bill bill;

}
