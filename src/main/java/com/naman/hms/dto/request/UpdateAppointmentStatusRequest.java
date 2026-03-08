package com.naman.hms.dto.request;

import com.naman.hms.enums.AppointmentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAppointmentStatusRequest {

    @NotNull
    private AppointmentStatus status;
}