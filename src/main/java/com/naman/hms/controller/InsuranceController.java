package com.naman.hms.controller;

import com.naman.hms.dto.request.CreateInsuranceRequest;
import com.naman.hms.dto.request.UpdateInsuranceRequest;
import com.naman.hms.dto.response.InsuranceResponse;
import com.naman.hms.service.InsuranceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insurances")
@RequiredArgsConstructor
public class InsuranceController {

    private final InsuranceService insuranceService;

    @PostMapping
    public InsuranceResponse create(@Valid @RequestBody CreateInsuranceRequest request) {
        return insuranceService.createInsurance(request);
    }

    @GetMapping("/{id}")
    public InsuranceResponse get(@PathVariable Long id) {
        return insuranceService.getInsurance(id);
    }

    @GetMapping("/valid")
    public List<InsuranceResponse> getValid() {
        return insuranceService.getValidInsurances();
    }

    @GetMapping("/policy/{policyNumber}")
    public InsuranceResponse getByPolicy(@PathVariable String policyNumber) {
        return insuranceService.getByPolicyNumber(policyNumber);
    }

    @PutMapping("/{id}")
    public InsuranceResponse update(@PathVariable Long id,
                                    @Valid @RequestBody UpdateInsuranceRequest request) {
        return insuranceService.updateInsurance(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        insuranceService.deleteInsurance(id);
    }
}