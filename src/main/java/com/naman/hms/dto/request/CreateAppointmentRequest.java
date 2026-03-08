package com.naman.hms.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateAppointmentRequest {

    @NotNull
    private Long patientId;
    @NotNull
    private Long doctorId;
    @Size(max=500)
    private String reason;
    @NotNull
    private LocalDateTime appointmentTime;

}
