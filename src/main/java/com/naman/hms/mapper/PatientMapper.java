package com.naman.hms.mapper;

import com.naman.hms.dto.request.PatientRequest;

import com.naman.hms.dto.response.PatientResponse;
import com.naman.hms.entity.Insurance;
import com.naman.hms.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public Patient toEntity(PatientRequest request, Insurance insurance) {

        Patient patient = new Patient();

        patient.setName(request.getName());
        patient.setEmail(request.getEmail());
        patient.setPhone(request.getPhone());
        patient.setGender(request.getGender());
        patient.setDateOfBirth(request.getDateOfBirth());
        patient.setBloodGroup(request.getBloodGroup());
        patient.setInsurance(insurance);

        return patient;
    }

    public PatientResponse toResponse(Patient patient) {

        PatientResponse response = new PatientResponse();

        response.setId(patient.getId());
        response.setName(patient.getName());
        response.setEmail(patient.getEmail());
        response.setPhone(patient.getPhone());
        response.setGender(patient.getGender());
        response.setDateOfBirth(patient.getDateOfBirth());
        response.setBloodGroup(patient.getBloodGroup());

        if (patient.getInsurance() != null) {
            response.setInsuranceId(patient.getInsurance().getId());
        }

        response.setCreatedAt(patient.getCreatedAt());
        response.setUpdatedAt(patient.getUpdatedAt());

        return response;
    }
}
