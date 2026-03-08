package com.naman.hms.service;

import com.naman.hms.dto.request.CreateDoctorRequest;
import com.naman.hms.dto.request.UpdateDoctorRequest;
import com.naman.hms.dto.response.DoctorResponse;
import com.naman.hms.dto.response.DoctorSummaryResponse;
import com.naman.hms.enums.Specialization;

import java.util.List;

public interface DoctorService {
    DoctorResponse createDoctor(CreateDoctorRequest createDoctorRequest);

    DoctorResponse getDoctor(Long id);

    List<DoctorResponse> getAllDoctors();

    DoctorResponse updateDoctor(Long id,UpdateDoctorRequest doctorRequest);

    void deleteById(Long id);

    List<DoctorSummaryResponse> getDoctorsBySpecialization(Specialization specialization);
}
