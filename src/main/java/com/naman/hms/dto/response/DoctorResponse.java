package com.naman.hms.dto.response;

import com.naman.hms.enums.Specialization;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DoctorResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Specialization specialization;

    private boolean headOfDepartment;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
