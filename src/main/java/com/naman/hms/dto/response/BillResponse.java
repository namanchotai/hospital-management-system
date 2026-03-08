package com.naman.hms.dto.response;

import com.naman.hms.enums.BillStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BillResponse {

    private Long id;
    private BigDecimal amount;
    private BillStatus status;
    private Long appointmentId;
    private Long patientId;
    private Long doctorId;
}