package com.naman.hms.service;

import com.naman.hms.dto.request.CreateDepartmentRequest;
import com.naman.hms.dto.response.DepartmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {

    DepartmentResponse createDepartment(CreateDepartmentRequest departmentRequest);

    DepartmentResponse assignHeadDoctor(Long id, Long doctorId);

    void addDoctorToDepartment(Long id, Long doctorId);

    void removeDoctorFromDepartment(Long id, Long doctorId);

    DepartmentResponse getDepartmentById(Long id);

    Page<DepartmentResponse> getAllDepartments(Pageable pageable);

    DepartmentResponse updateDepartment(Long id, CreateDepartmentRequest request);

    void deleteDepartment(Long id);
}
