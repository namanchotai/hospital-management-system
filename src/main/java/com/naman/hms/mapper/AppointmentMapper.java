package com.naman.hms.mapper;

import com.naman.hms.dto.response.AppointmentResponse;
import com.naman.hms.entity.Appointment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppointmentMapper {

    public AppointmentResponse toResponse(Appointment appointment) {

        AppointmentResponse response = new AppointmentResponse();

        response.setId(appointment.getId());

        response.setPatientId(appointment.getPatient().getId());
        response.setPatientName(appointment.getPatient().getName());

        response.setDoctorId(appointment.getDoctor().getId());
        response.setDoctorName(appointment.getDoctor().getName());

        response.setAppointmentTime(appointment.getAppointmentTime());
        response.setStatus(appointment.getStatus());
        response.setReason(appointment.getReason());

        if (appointment.getBill() != null) {
            response.setBillId(appointment.getBill().getId());
            response.setBillAmount(appointment.getBill().getAmount());
        }

        return response;
    }
    public List<AppointmentResponse> toResponseList(List<Appointment> appointments) {
        return appointments
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}