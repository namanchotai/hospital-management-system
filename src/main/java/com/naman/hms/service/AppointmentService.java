package com.naman.hms.service;

import com.naman.hms.dto.request.CreateAppointmentRequest;
import com.naman.hms.dto.request.UpdateAppointmentStatusRequest;
import com.naman.hms.dto.response.AppointmentResponse;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {
    AppointmentResponse createNewAppointment(CreateAppointmentRequest createAppointmentRequest);

    AppointmentResponse getAppointmentDetails(Long id);

    List<AppointmentResponse> getAllAppointments();

    List<AppointmentResponse> getAppointmentsByDoctor(Long doctorId);

    List<AppointmentResponse> getAppointmentsByPatient(Long patientId);

    List<AppointmentResponse> getAppointmentsByDateRange(LocalDateTime start, LocalDateTime end);

    AppointmentResponse updateAppointmentStatus(Long id, UpdateAppointmentStatusRequest request);

    void cancelAppointment(Long id);
}
