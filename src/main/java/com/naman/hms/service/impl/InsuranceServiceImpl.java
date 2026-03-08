package com.naman.hms.service.impl;

import com.naman.hms.dto.request.CreateInsuranceRequest;
import com.naman.hms.dto.request.UpdateInsuranceRequest;
import com.naman.hms.dto.response.InsuranceResponse;
import com.naman.hms.entity.Insurance;
import com.naman.hms.entity.Patient;
import com.naman.hms.exception.BusinessException;
import com.naman.hms.exception.ResourceNotFoundException;
import com.naman.hms.mapper.InsuranceMapper;
import com.naman.hms.repository.InsuranceRepository;
import com.naman.hms.repository.PatientRepository;
import com.naman.hms.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;
    private final InsuranceMapper insuranceMapper;

    @Override
    public InsuranceResponse createInsurance(CreateInsuranceRequest request) {

        if (insuranceRepository.existsByPolicyNumber(request.getPolicyNumber())) {
            throw new BusinessException("Policy number already exists");
        }

        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        if (patient.getInsurance() != null) {
            throw new BusinessException("Patient already has insurance");
        }

        Insurance insurance = new Insurance();
        insurance.setPolicyNumber(request.getPolicyNumber());
        insurance.setProvider(request.getProvider());
        insurance.setValidUntil(request.getValidUntil());

        Insurance savedInsurance = insuranceRepository.save(insurance);

        patient.setInsurance(savedInsurance);
        patientRepository.save(patient);

        return insuranceMapper.toResponse(savedInsurance, patient.getId());
    }

    @Override
    public InsuranceResponse getInsurance(Long id) {

        Insurance insurance = insuranceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insurance not found"));

        Long patientId = patientRepository.existsByInsuranceId(id) ? id : null;

        return insuranceMapper.toResponse(insurance, patientId);
    }

    @Override
    public List<InsuranceResponse> getValidInsurances() {

        return insuranceRepository
                .findByValidUntilAfter(LocalDate.now())
                .stream()
                .map(i -> insuranceMapper.toResponse(i, null))
                .toList();
    }

    @Override
    public InsuranceResponse getByPolicyNumber(String policyNumber) {

        Insurance insurance = insuranceRepository.findByPolicyNumber(policyNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Insurance not found"));

        return insuranceMapper.toResponse(insurance, null);
    }

    @Override
    public InsuranceResponse updateInsurance(Long id, UpdateInsuranceRequest request) {

        Insurance insurance = insuranceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insurance not found"));

        insurance.setProvider(request.getProvider());
        insurance.setValidUntil(request.getValidUntil());

        Insurance updated = insuranceRepository.save(insurance);

        return insuranceMapper.toResponse(updated, null);
    }

    @Override
    public void deleteInsurance(Long id) {

        Insurance insurance = insuranceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insurance not found"));

        Patient patient = patientRepository.findAll()
                .stream()
                .filter(p -> p.getInsurance() != null && p.getInsurance().getId().equals(id))
                .findFirst()
                .orElse(null);

        if (patient != null) {
            patient.setInsurance(null);
            patientRepository.save(patient);
        }

        insuranceRepository.delete(insurance);
    }
}