package com.naman.hms.entity;

import com.naman.hms.enums.BloodGroup;
import com.naman.hms.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="patients")
@Getter
@Setter
public class Patient extends BaseEntity{
    @Column(nullable=false)
    private String name;
    @Column(nullable=false,unique = true)
    private String email;
    @Column(nullable=false,length = 15)
    private String phone;
    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(nullable=false)
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "insurance_id", unique = true)
    private Insurance insurance;
    @OneToMany(mappedBy = "patient")
    private Set<Appointment> appointments;
}
