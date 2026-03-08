package com.naman.hms.dto.response;

import com.naman.hms.entity.Department;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DepartmentResponse {
    private Long id;
    private String name;
    private Long headDoctorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
