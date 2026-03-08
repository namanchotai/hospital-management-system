package com.naman.hms.dto.response;

import com.naman.hms.enums.Specialization;
import lombok.Builder;
import lombok.Data;


@Data

public class DoctorSummaryResponse {
    private Long id;
    private String name;
    private Specialization specialization;
}
