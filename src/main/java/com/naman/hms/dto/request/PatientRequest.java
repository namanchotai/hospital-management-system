package com.naman.hms.dto.request;

import com.naman.hms.enums.BloodGroup;
import com.naman.hms.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Data;


import java.time.LocalDate;

@Data
public class PatientRequest {
    @NotBlank(message = "Name is Required")
    @Size(max = 100,message = "Name must not exceed 100 characters")
    private String name;
    @NotBlank(message = "Email is Required")
    @Email(message = "Email must be Valid")
    @Size(max=100,message = "Email must not exceed 100 characters")
    private String email;
    @NotBlank(message = "Phone number is required")
    @Size(max=15,message = "Phone number must not exceed 15 characters")
    private String phone;
    @NotNull(message = "Gender is required")
    private Gender gender;
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
    //Optional fields
    private BloodGroup bloodGroup;
    private Long insuranceId;
}
