package com.naman.hms.mapper;

import com.naman.hms.dto.response.InsuranceResponse;
import com.naman.hms.entity.Insurance;
import org.springframework.stereotype.Component;

@Component
public class InsuranceMapper {

    public InsuranceResponse toResponse(Insurance insurance, Long patientId) {
        InsuranceResponse response = new InsuranceResponse();

        response.setId(insurance.getId());
        response.setPolicyNumber(insurance.getPolicyNumber());
        response.setProvider(insurance.getProvider());
        response.setValidUntil(insurance.getValidUntil());
        response.setPatientId(patientId);

        return response;
    }

}