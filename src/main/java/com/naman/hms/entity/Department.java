package com.naman.hms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Department extends  BaseEntity{
    @Column(unique = true,nullable = false)
    private String name;
    @JoinColumn(name = "head_doctor_id", unique = true,nullable = true)
    @OneToOne(fetch = FetchType.LAZY)
    private Doctor headDoctor;
    @ManyToMany(mappedBy = "departments")
    private Set<Doctor> doctors;
}
