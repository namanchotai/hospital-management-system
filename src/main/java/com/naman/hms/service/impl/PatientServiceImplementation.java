package com.naman.hms.service.impl;

import com.naman.hms.common.response.ApiResponse;
import com.naman.hms.dto.request.PatientRequest;
import com.naman.hms.dto.response.PatientResponse;
import com.naman.hms.entity.Insurance;
import com.naman.hms.entity.Patient;
import com.naman.hms.exception.BusinessException;
import com.naman.hms.exception.DuplicateResourceException;
import com.naman.hms.exception.ResourceNotFoundException;
import com.naman.hms.mapper.PatientMapper;
import com.naman.hms.repository.AppointmentRepository;
import com.naman.hms.repository.InsuranceRepository;
import com.naman.hms.repository.PatientRepository;
import com.naman.hms.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImplementation implements PatientService {

    private final PatientRepository patientRepository;
    private final InsuranceRepository insuranceRepository;
    private final AppointmentRepository appointmentRepository;
    private final PatientMapper patientMapper;

    @Override
    @Transactional
    public PatientResponse createPatient(PatientRequest patientDTO) {

        //checking if user already exists or not
        if(patientRepository.existsByEmail(patientDTO.getEmail())) {
            throw new DuplicateResourceException("Patient with email "+patientDTO.getEmail()+" already exists");
        }

        //checking if age is greater than 1 year or not
        int age= Period.between(patientDTO.getDateOfBirth(), LocalDate.now()).getYears();
        if(age<1){
            throw new BusinessException("Patient must be atleast 1 year old");
        }

        //optional insurance linking
        Insurance insurance=null;
        if(patientDTO.getInsuranceId()!=null){
            insurance=insuranceRepository.findById(patientDTO.getInsuranceId()).
                    orElseThrow(()->new ResourceNotFoundException("Insurance with ID " + patientDTO.getInsuranceId() + " not found"));
        }

        Patient patient = patientMapper.toEntity(patientDTO, insurance);

        Patient savedPatient = patientRepository.save(patient);

        PatientResponse responseDTO = patientMapper.toResponse(savedPatient);

        return responseDTO;

    }

    @Override
    @Transactional(readOnly = true)
    public PatientResponse getPatientById(Long id) {
        Patient patient=patientRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Patient with ID " + id + " not found"));
        PatientResponse responseDTO = patientMapper.toResponse(patient);
        return responseDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientResponse> getAllPatients() {

        List<Patient> patients = patientRepository.findAll();

        return patients.stream()
                .map(patientMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public PatientResponse updatePatient(Long id, PatientRequest patientDTO) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient with ID " + id + " not found")
                );

        patient.setName(patientDTO.getName());
        patient.setEmail(patientDTO.getEmail());
        patient.setPhone(patientDTO.getPhone());
        patient.setGender(patientDTO.getGender());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
        patient.setBloodGroup(patientDTO.getBloodGroup());

        if (patientDTO.getInsuranceId() != null) {
            Insurance insurance = insuranceRepository.findById(patientDTO.getInsuranceId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Insurance not found")
                    );
            patient.setInsurance(insurance);
        } else {
            patient.setInsurance(null);
        }

        Patient updatedPatient = patientRepository.save(patient);

        return patientMapper.toResponse(updatedPatient);
    }


    @Override
    @Transactional
    public void deleteById(Long id) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient with ID " + id + " not found")
                );

        if (appointmentRepository.existsByPatientId(id)) {
            throw new BusinessException("Cannot delete patient with existing appointments");
        }

        patientRepository.delete(patient);
    }

}
