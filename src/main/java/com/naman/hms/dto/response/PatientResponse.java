package com.naman.hms.dto.response;

import com.naman.hms.enums.BloodGroup;
import com.naman.hms.enums.Gender;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PatientResponse {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private Gender gender;

    private LocalDate dateOfBirth;

    private BloodGroup bloodGroup;

    private Long insuranceId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
