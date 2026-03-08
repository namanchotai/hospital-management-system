package com.naman.hms.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InsuranceResponse {

    private Long id;
    private String policyNumber;
    private String provider;
    private LocalDate validUntil;
    private Long patientId;

}