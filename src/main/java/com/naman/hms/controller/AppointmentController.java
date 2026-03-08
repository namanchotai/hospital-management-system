package com.naman.hms.controller;

import com.naman.hms.common.response.ApiResponse;
import com.naman.hms.dto.request.CreateAppointmentRequest;
import com.naman.hms.dto.request.UpdateAppointmentStatusRequest;
import com.naman.hms.dto.response.AppointmentResponse;
import com.naman.hms.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    @PostMapping
    public ResponseEntity<ApiResponse<AppointmentResponse>> createAppointment(
            @Valid
            @RequestBody CreateAppointmentRequest createAppointmentRequest){
        AppointmentResponse appointmentResponse = appointmentService
                .createNewAppointment(createAppointmentRequest);
        ApiResponse<AppointmentResponse> apiResponse= ApiResponse.<AppointmentResponse>builder()
                .success(true)
                .data(appointmentResponse)
                .message("Appointment Created Successfully").build();
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AppointmentResponse>> getAppointment(@PathVariable Long id){
        AppointmentResponse appointmentResponse=appointmentService.getAppointmentDetails(id);
        ApiResponse<AppointmentResponse> apiResponse=ApiResponse
                .<AppointmentResponse>builder()
                .data(appointmentResponse)
                .message("Appointment Details Found Successfully")
                .success(true)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AppointmentResponse>>> getAllAppointments(){

        List<AppointmentResponse> appointments = appointmentService.getAllAppointments();

        ApiResponse<List<AppointmentResponse>> apiResponse =
                ApiResponse.<List<AppointmentResponse>>builder()
                        .success(true)
                        .message("All Appointments Fetched Successfully")
                        .data(appointments)
                        .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<ApiResponse<List<AppointmentResponse>>> getAppointmentsByDoctor(
            @PathVariable Long doctorId){

        List<AppointmentResponse> appointments =
                appointmentService.getAppointmentsByDoctor(doctorId);

        ApiResponse<List<AppointmentResponse>> apiResponse =
                ApiResponse.<List<AppointmentResponse>>builder()
                        .success(true)
                        .message("Doctor Appointments Fetched Successfully")
                        .data(appointments)
                        .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<ApiResponse<List<AppointmentResponse>>> getAppointmentsByPatient(
            @PathVariable Long patientId){

        List<AppointmentResponse> appointments =
                appointmentService.getAppointmentsByPatient(patientId);

        ApiResponse<List<AppointmentResponse>> apiResponse =
                ApiResponse.<List<AppointmentResponse>>builder()
                        .success(true)
                        .message("Patient Appointments Fetched Successfully")
                        .data(appointments)
                        .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/date-range")
    public ResponseEntity<ApiResponse<List<AppointmentResponse>>> getAppointmentsByDateRange(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end){

        List<AppointmentResponse> appointments =
                appointmentService.getAppointmentsByDateRange(start,end);

        ApiResponse<List<AppointmentResponse>> apiResponse =
                ApiResponse.<List<AppointmentResponse>>builder()
                        .success(true)
                        .message("Appointments Found In Date Range")
                        .data(appointments)
                        .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<AppointmentResponse>> updateAppointmentStatus(
            @PathVariable Long id,
            @Valid
            @RequestBody UpdateAppointmentStatusRequest request){

        AppointmentResponse appointmentResponse =
                appointmentService.updateAppointmentStatus(id, request);

        ApiResponse<AppointmentResponse> apiResponse =
                ApiResponse.<AppointmentResponse>builder()
                        .success(true)
                        .message("Appointment Status Updated Successfully")
                        .data(appointmentResponse)
                        .build();

        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> cancelAppointment(@PathVariable Long id){

        appointmentService.cancelAppointment(id);

        ApiResponse<Void> apiResponse =
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Appointment Cancelled Successfully")
                        .build();

        return ResponseEntity.ok(apiResponse);
    }
}
