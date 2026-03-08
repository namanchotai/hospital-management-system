package com.naman.hms.controller;

import com.naman.hms.common.response.ApiResponse;
import com.naman.hms.dto.request.PatientRequest;
import com.naman.hms.dto.response.PatientResponse;
import com.naman.hms.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.dsig.spec.RSAPSSParameterSpec;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<ApiResponse<PatientResponse>> createPatient(@Valid @RequestBody PatientRequest patientDTO) {
        PatientResponse patientResponse= patientService.createPatient(patientDTO);
        ApiResponse<PatientResponse> apiResponse = ApiResponse.<PatientResponse>builder()
                .success(true)
                .message("Patient created successfully")
                .data(patientResponse)
                .build();

        return ResponseEntity.status(201).body(apiResponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PatientResponse>> getPatient(@PathVariable Long id) {
        PatientResponse patientResponse= patientService.getPatientById(id);
        ApiResponse<PatientResponse> apiResponse=ApiResponse.<PatientResponse>builder()
                .success(true)
                .message("Patient fetched successfully")
                .data(patientResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PatientResponse>>> getAllPatients() {
        List<PatientResponse> patientResponseList=patientService.getAllPatients();
        ApiResponse<List<PatientResponse>> response=ApiResponse.<List<PatientResponse>>builder()
                .success(true)
                .message("Patients fetched successfully")
                .data(patientResponseList)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PatientResponse>> updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody PatientRequest patientDTO){
        PatientResponse patientResponse=patientService.updatePatient(id,patientDTO);
        ApiResponse<PatientResponse> response=ApiResponse.<PatientResponse>builder()
                .success(true)
                .message("Patient updated successfully")
                .data(patientResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
