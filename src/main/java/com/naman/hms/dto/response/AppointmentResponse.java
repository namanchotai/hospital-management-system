package com.naman.hms.dto.response;

import com.naman.hms.enums.AppointmentStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentResponse {

    private Long id;

    private Long patientId;
    private String patientName;

    private Long doctorId;
    private String doctorName;

    private LocalDateTime appointmentTime;

    private AppointmentStatus status;

    private String reason;

    private Long billId;

    private BigDecimal billAmount;

}
