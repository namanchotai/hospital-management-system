package com.naman.hms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="insurances")
@Getter
@Setter
public class Insurance extends BaseEntity {

    @Column(name = "policy_number", unique = true, nullable = false)
    private String policyNumber;

    @Column(nullable = false)
    private String provider;

    @Column(name = "valid_until", nullable = false)
    private LocalDate validUntil;
}