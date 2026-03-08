package com.naman.hms.controller;

import com.naman.hms.common.response.ApiResponse;
import com.naman.hms.dto.request.CreateDoctorRequest;

import com.naman.hms.dto.request.UpdateDoctorRequest;
import com.naman.hms.dto.response.DoctorResponse;
import com.naman.hms.dto.response.DoctorSummaryResponse;

import com.naman.hms.enums.Specialization;
import com.naman.hms.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<ApiResponse<DoctorResponse>> createDoctor(
            @Valid @RequestBody CreateDoctorRequest createDoctorRequest){
        DoctorResponse doctorResponse= doctorService.createDoctor(createDoctorRequest);
        ApiResponse<DoctorResponse> apiResponse= ApiResponse.<DoctorResponse>builder()
                .success(true)
                .message("Doctor created successfully")
                .data(doctorResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DoctorResponse>> getDoctorById(@PathVariable Long id){
        DoctorResponse doctorResponse=doctorService.getDoctor(id);
        ApiResponse<DoctorResponse> apiResponse= ApiResponse.<DoctorResponse>builder()
                .success(true)
                .message("Doctor fetched successfully")
                .data(doctorResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DoctorResponse>>> getAllDoctors(){
        List<DoctorResponse> doctorResponse=doctorService.getAllDoctors();
        ApiResponse<List<DoctorResponse>> apiResponse= ApiResponse.<List<DoctorResponse>>builder()
                .success(true)
                .message("Doctors fetched successfully")
                .data(doctorResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DoctorResponse>> updateDoctor(
            @PathVariable Long id,
            @Valid @RequestBody UpdateDoctorRequest doctorRequest){
        DoctorResponse doctorResponse=doctorService.updateDoctor(id, doctorRequest);
        ApiResponse<DoctorResponse> apiResponse=ApiResponse.<DoctorResponse>builder()
                .success(true)
                .message("Doctor updates successfully!")
                .data(doctorResponse)
                .build();
        return  ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id){
        doctorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<ApiResponse<List<DoctorSummaryResponse>>> getAllDoctorsBySpecialization(@PathVariable Specialization specialization){
        List<DoctorSummaryResponse> doctors= doctorService.getDoctorsBySpecialization(specialization);
        ApiResponse<List<DoctorSummaryResponse>> apiResponse=ApiResponse.<List<DoctorSummaryResponse>>builder()
                .success(true)
                .message("Doctor fetched successfully!")
                .data(doctors)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
