package com.naman.hms.mapper;

import com.naman.hms.dto.request.CreateDoctorRequest;
import com.naman.hms.dto.request.UpdateDoctorRequest;
import com.naman.hms.dto.response.DoctorResponse;
import com.naman.hms.dto.response.DoctorSummaryResponse;
import com.naman.hms.entity.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    public void updateEntity(UpdateDoctorRequest request, Doctor doctor) {

        if (request.getName() != null) {
            doctor.setName(request.getName());
        }

        if (request.getEmail() != null) {
            doctor.setEmail(request.getEmail());
        }

        if (request.getPhone() != null) {
            doctor.setPhone(request.getPhone());
        }

        if (request.getSpecialization() != null) {
            doctor.setSpecialization(request.getSpecialization());
        }
    }

    public Doctor toEntity(CreateDoctorRequest request) {
        Doctor doctor = new Doctor();
        doctor.setName(request.getName());
        doctor.setEmail(request.getEmail());
        doctor.setPhone(request.getPhone());
        doctor.setSpecialization(request.getSpecialization());
        return doctor;
    }

    public DoctorResponse toResponse(Doctor doctor) {

        DoctorResponse response = new DoctorResponse();
        response.setId(doctor.getId());
        response.setName(doctor.getName());
        response.setEmail(doctor.getEmail());
        response.setPhone(doctor.getPhone());
        response.setSpecialization(doctor.getSpecialization());

        response.setHeadOfDepartment(doctor.getHeadedDepartment() != null);

        response.setCreatedAt(doctor.getCreatedAt());
        response.setUpdatedAt(doctor.getUpdatedAt());

        return response;
    }

    public DoctorSummaryResponse toSummaryResponse(Doctor doctor) {

        DoctorSummaryResponse summary = new DoctorSummaryResponse();
        summary.setId(doctor.getId());
        summary.setName(doctor.getName());
        summary.setSpecialization(doctor.getSpecialization());

        return summary;
    }
}
