package com.naman.hms.service;

import com.naman.hms.dto.request.CreateInsuranceRequest;
import com.naman.hms.dto.request.UpdateInsuranceRequest;
import com.naman.hms.dto.response.InsuranceResponse;

import java.util.List;

public interface InsuranceService {

    InsuranceResponse createInsurance(CreateInsuranceRequest request);

    InsuranceResponse getInsurance(Long id);

    List<InsuranceResponse> getValidInsurances();

    InsuranceResponse getByPolicyNumber(String policyNumber);

    InsuranceResponse updateInsurance(Long id, UpdateInsuranceRequest request);

    void deleteInsurance(Long id);

}