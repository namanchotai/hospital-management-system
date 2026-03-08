package com.naman.hms.entity;

import com.naman.hms.enums.Specialization;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="doctors")
@Getter
@Setter
public class Doctor extends BaseEntity {
    @Column(nullable=false)
    private String name;
    @Column(nullable=false,unique=true)
    private String email;
    @Column(nullable=false,length = 15)
    private String phone;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Specialization specialization;
    @OneToOne(mappedBy = "headDoctor")
    private Department headedDepartment;

    @ManyToMany
    @JoinTable(
            name="doctor_department",
            joinColumns = @JoinColumn(name="doctor_id"),
            inverseJoinColumns = @JoinColumn(name="department_id")
    )
    private Set<Department> departments;

    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY)
    private Set<Appointment> appointments;
}
