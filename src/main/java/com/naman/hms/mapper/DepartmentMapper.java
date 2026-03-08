package com.naman.hms.mapper;

import com.naman.hms.dto.request.CreateDepartmentRequest;
import com.naman.hms.dto.response.DepartmentResponse;
import com.naman.hms.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    public Department toEntity(CreateDepartmentRequest request) {
        if (request == null) {
            return null;
        }

        Department department = new Department();
        department.setName(request.getName());

        return department;
    }

    public DepartmentResponse toResponse(Department department) {
        if (department == null) {
            return null;
        }

        DepartmentResponse response = new DepartmentResponse();
        response.setId(department.getId());
        response.setName(department.getName());

        if (department.getHeadDoctor() != null) {
            response.setHeadDoctorId(department.getHeadDoctor().getId());
        }

        response.setCreatedAt(department.getCreatedAt());
        response.setUpdatedAt(department.getUpdatedAt());

        return response;
    }
}