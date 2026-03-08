package com.naman.hms.service;


import com.naman.hms.dto.request.PatientRequest;
import com.naman.hms.dto.response.PatientResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface PatientService {

    PatientResponse createPatient(PatientRequest request);

    PatientResponse getPatientById(Long id);

    List<PatientResponse> getAllPatients();

    PatientResponse updatePatient(Long id, @Valid PatientRequest patientDTO);

    void deleteById(Long id);
}
