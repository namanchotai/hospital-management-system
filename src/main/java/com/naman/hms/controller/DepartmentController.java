package com.naman.hms.controller;

import com.naman.hms.common.response.ApiResponse;
import com.naman.hms.dto.request.CreateDepartmentRequest;
import com.naman.hms.dto.response.DepartmentResponse;
import com.naman.hms.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentResponse>> getDepartment(@PathVariable Long id) {

        DepartmentResponse response = departmentService.getDepartmentById(id);

        return ResponseEntity.ok(
                ApiResponse.<DepartmentResponse>builder()
                        .success(true)
                        .message("Department fetched successfully")
                        .data(response)
                        .build()
        );
    }
    @GetMapping
    public ResponseEntity<ApiResponse<Page<DepartmentResponse>>> getAllDepartments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<DepartmentResponse> response =
                departmentService.getAllDepartments(pageable);

        return ResponseEntity.ok(
                ApiResponse.<Page<DepartmentResponse>>builder()
                        .success(true)
                        .message("Departments fetched successfully")
                        .data(response)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentResponse>> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody CreateDepartmentRequest request) {

        DepartmentResponse response =
                departmentService.updateDepartment(id, request);

        ApiResponse<DepartmentResponse> apiResponse=ApiResponse.<DepartmentResponse>builder()
                .success(true)
                .message("Department updated successfully")
                .data(response)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDepartment(@PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Department deleted successfully")
                        .data(null)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentResponse>> addDepartment(
            @RequestBody @Valid CreateDepartmentRequest departmentRequest
    ){
        DepartmentResponse departmentResponse = departmentService.createDepartment(departmentRequest);
        ApiResponse<DepartmentResponse> apiResponse= ApiResponse
                .<DepartmentResponse>builder()
                .data(departmentResponse)
                .message("Department Created")
                .success(true)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}/head-doctor/{doctorId}")
    public ResponseEntity<ApiResponse<DepartmentResponse>> assignHeadDoctor(
            @PathVariable Long id,
            @PathVariable Long doctorId) {

        DepartmentResponse response =
                departmentService.assignHeadDoctor(id, doctorId);

        ApiResponse<DepartmentResponse> apiResponse = ApiResponse
                .<DepartmentResponse>builder()
                .success(true)
                .message("Head doctor assigned successfully")
                .data(response)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/{id}/doctors/{doctorId}")
    public ResponseEntity<ApiResponse<Void>> addDoctorToDepartment(
            @PathVariable Long id,
            @PathVariable Long doctorId) {

        departmentService.addDoctorToDepartment(id, doctorId);

        ApiResponse<Void> apiResponse = ApiResponse
                .<Void>builder()
                .success(true)
                .message("Doctor added to department successfully")
                .data(null)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}/doctors/{doctorId}")
    public ResponseEntity<ApiResponse<Void>> removeDoctorFromDepartment(
            @PathVariable Long id,
            @PathVariable Long doctorId) {

        departmentService.removeDoctorFromDepartment(id, doctorId);

        ApiResponse<Void> apiResponse = ApiResponse
                .<Void>builder()
                .success(true)
                .message("Doctor removed from department successfully")
                .data(null)
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}
