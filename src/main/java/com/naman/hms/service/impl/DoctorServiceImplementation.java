package com.naman.hms.service.impl;

import com.naman.hms.dto.request.CreateDoctorRequest;
import com.naman.hms.dto.request.UpdateDoctorRequest;
import com.naman.hms.dto.response.DoctorResponse;
import com.naman.hms.dto.response.DoctorSummaryResponse;
import com.naman.hms.entity.Doctor;
import com.naman.hms.enums.Specialization;
import com.naman.hms.exception.DuplicateResourceException;
import com.naman.hms.exception.ResourceNotFoundException;
import com.naman.hms.mapper.DoctorMapper;
import com.naman.hms.repository.DoctorRepository;
import com.naman.hms.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImplementation implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Override
    @Transactional
    public DoctorResponse createDoctor(CreateDoctorRequest createDoctorRequest) {
        if(doctorRepository.existsByEmail(createDoctorRequest.getEmail())){
            throw new DuplicateResourceException("Doctor with this email already exists");
        }
        Doctor doctor = doctorMapper.toEntity(createDoctorRequest);
        doctor = doctorRepository.save(doctor);
        return doctorMapper.toResponse(doctor);
    }

    @Override
    public DoctorResponse getDoctor(Long id) {
        Doctor doctor=doctorRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Doctor with ID "+id+" not found"));
        return doctorMapper.toResponse(doctor);
    }

    @Override
    public List<DoctorResponse> getAllDoctors() {
        List<Doctor> doctors=doctorRepository.findAll();
        if(doctors.isEmpty()){
            throw new ResourceNotFoundException("Doctors not found");
        }
        List<DoctorResponse> doctorResponseList=doctors
                .stream()
                .map(doctorMapper::toResponse).toList();

        return doctorResponseList;
    }

    @Override
    @Transactional
    public DoctorResponse updateDoctor(Long id, UpdateDoctorRequest updateDoctorRequest){
        if(!doctorRepository.existsById(id)){
            throw new ResourceNotFoundException("Doctor with ID "+id+" not found");
        }
        Doctor doctor=doctorRepository.findById(id).get();
        if(!doctor.getEmail().equals(updateDoctorRequest.getEmail())){
            if(doctorRepository.existsByEmail(updateDoctorRequest.getEmail())){
                throw new DuplicateResourceException("Doctor with this email already exists");
            }
        }
        doctor.setName(updateDoctorRequest.getName());
        doctor.setEmail(updateDoctorRequest.getEmail());
        doctor.setPhone(updateDoctorRequest.getPhone());
        doctorRepository.save(doctor);
        return doctorMapper.toResponse(doctor);
    }

    @Override
    public void deleteById(Long id) {
        if(doctorRepository.existsById(id)){
            doctorRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException("Doctor with ID "+id+" not found");
        }
    }

    @Override
    public List<DoctorSummaryResponse> getDoctorsBySpecialization(Specialization specialization) {
        System.out.println("Endpoint Service :::");
        List<Doctor> doctorsBySpecialization=doctorRepository.getBySpecialization(specialization);
        if(doctorsBySpecialization.isEmpty()){
            throw new ResourceNotFoundException("Doctors not found with specialization");

        }
        return doctorsBySpecialization
                .stream()
                .map(doctorMapper::toSummaryResponse)
                .toList();
    }


}
