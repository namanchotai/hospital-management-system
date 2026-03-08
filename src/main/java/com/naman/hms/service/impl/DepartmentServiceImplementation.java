package com.naman.hms.service.impl;

import com.naman.hms.dto.request.CreateDepartmentRequest;
import com.naman.hms.dto.response.DepartmentResponse;
import com.naman.hms.entity.Department;
import com.naman.hms.entity.Doctor;
import com.naman.hms.exception.BusinessException;
import com.naman.hms.exception.DuplicateResourceException;
import com.naman.hms.exception.ResourceNotFoundException;
import com.naman.hms.mapper.DepartmentMapper;
import com.naman.hms.repository.DepartmentRepository;
import com.naman.hms.repository.DoctorRepository;
import com.naman.hms.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentServiceImplementation implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;
    private final DepartmentMapper departmentMapper;


    @Override
    public DepartmentResponse createDepartment(CreateDepartmentRequest departmentRequest) {
        if(departmentRepository.existsByName(departmentRequest.getName())){
            throw new BusinessException("Department with name " + departmentRequest.getName() + " already exists");
        }
        Department department = new Department();
        department.setName(departmentRequest.getName());
        departmentRepository.save(department);
        return departmentMapper.toResponse(department);

    }

    @Override
    @Transactional
    public DepartmentResponse assignHeadDoctor(Long departmentId, Long doctorId) {
        Department department=departmentRepository
                .findById(departmentId)
                .orElseThrow(()->new ResourceNotFoundException("Department with id "+departmentId+" not found"));
        Doctor doctor=doctorRepository
                .findById(doctorId)
                .orElseThrow(()->new ResourceNotFoundException("Doctor with id "+doctorId+" not found"));
        if(!doctor.getDepartments().contains(department)){
            throw new BusinessException("Doctor must belong to department before becoming head");
        }
        if (doctor.getHeadedDepartment() != null &&
                !doctor.getHeadedDepartment().getId().equals(departmentId)) {
            throw new BusinessException("Doctor is already head of another department");

        }
        department.setHeadDoctor(doctor);
        departmentRepository.save(department);
        return departmentMapper.toResponse(department);
    }

    @Override
    public void addDoctorToDepartment(Long departmentId, Long doctorId) {

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        if (doctor.getDepartments().contains(department)) {
            throw new BusinessException("Doctor already part of this department");
        }

        // Sync both sides
        doctor.getDepartments().add(department);
        department.getDoctors().add(doctor);

        // Save owning side
        doctorRepository.save(doctor);
    }

    @Override
    public void removeDoctorFromDepartment(Long departmentId, Long doctorId) {

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        // Prevent removing if doctor is head
        if (department.getHeadDoctor() != null &&
                department.getHeadDoctor().getId().equals(doctorId)) {
            throw new BusinessException("Cannot remove doctor who is head of department");
        }

        if (!doctor.getDepartments().contains(department)) {
            throw new BusinessException("Doctor not part of this department");
        }

        // Sync both sides
        doctor.getDepartments().remove(department);
        department.getDoctors().remove(doctor);

        doctorRepository.save(doctor);
    }
    @Override
    @Transactional(readOnly = true)
    public DepartmentResponse getDepartmentById(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        return departmentMapper.toResponse(department);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DepartmentResponse> getAllDepartments(Pageable pageable) {

        return departmentRepository.findAll(pageable)
                .map(departmentMapper::toResponse);
    }

    @Override
    public DepartmentResponse updateDepartment(Long id, CreateDepartmentRequest request) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        if (!department.getName().equals(request.getName())
                && departmentRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Department name already exists");
        }

        department.setName(request.getName());

        return departmentMapper.toResponse(department);
    }
    @Override
    public void deleteDepartment(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        // Remove head doctor relation
        if (department.getHeadDoctor() != null) {
            department.setHeadDoctor(null);
        }

        // Remove ManyToMany associations safely
        for (Doctor doctor : department.getDoctors()) {
            doctor.getDepartments().remove(department);
        }

        department.getDoctors().clear();

        departmentRepository.delete(department);
    }
}
