package com.naman.hms.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateInsuranceRequest {

    @NotNull
    private Long patientId;

    @NotBlank
    private String policyNumber;

    @NotBlank
    private String provider;

    @NotNull
    @FutureOrPresent
    private LocalDate validUntil;

}