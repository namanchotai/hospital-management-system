package com.naman.hms.service.impl;

import com.naman.hms.dto.request.CreateAppointmentRequest;
import com.naman.hms.dto.request.UpdateAppointmentStatusRequest;
import com.naman.hms.dto.response.AppointmentResponse;
import com.naman.hms.entity.Appointment;
import com.naman.hms.entity.Bill;
import com.naman.hms.entity.Doctor;
import com.naman.hms.entity.Patient;
import com.naman.hms.enums.AppointmentStatus;
import com.naman.hms.enums.BillStatus;
import com.naman.hms.exception.BusinessException;
import com.naman.hms.exception.ResourceNotFoundException;
import com.naman.hms.mapper.AppointmentMapper;
import com.naman.hms.repository.AppointmentRepository;
import com.naman.hms.repository.DoctorRepository;
import com.naman.hms.repository.PatientRepository;
import com.naman.hms.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AppointmentServiceImplementation implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentMapper appointmentMapper;
    @Override
    public AppointmentResponse createNewAppointment(CreateAppointmentRequest createAppointmentRequest) {
        Patient patient = patientRepository.findById(createAppointmentRequest.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        Doctor doctor = doctorRepository.findById(createAppointmentRequest.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        if(!(createAppointmentRequest.getAppointmentTime().isAfter(LocalDateTime.now()))) {
            throw new BusinessException("Invalid appointment time");
        }
        boolean isBusy=appointmentRepository
                .existsByDoctorIdAndAppointmentTimeAndStatusNot
                        (createAppointmentRequest.getDoctorId(),createAppointmentRequest.getAppointmentTime(),AppointmentStatus.CANCELLED);
        if(isBusy){
            throw new BusinessException("Doctor is already booked for this time slot");
        }
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentTime(createAppointmentRequest.getAppointmentTime());
        appointment.setReason(createAppointmentRequest.getReason());
        appointment.setStatus(AppointmentStatus.SCHEDULED);
        Bill bill = new Bill();
        bill.setAmount(BigDecimal.valueOf(500));
        bill.setStatus(BillStatus.PENDING);
        bill.setAppointment(appointment);
        appointment.setBill(bill);
        Appointment saved = appointmentRepository.save(appointment);
        return appointmentMapper.toResponse(saved);
    }

    @Override
    public AppointmentResponse getAppointmentDetails(Long id) {
        Appointment appointment=appointmentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
        return appointmentMapper.toResponse(appointment);
    }

    @Override
    public List<AppointmentResponse> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointmentMapper.toResponseList(appointments);
    }

    @Override
    public List<AppointmentResponse> getAppointmentsByDoctor(Long doctorId) {
        if (!doctorRepository.existsById(doctorId)) {
            throw new ResourceNotFoundException("Doctor not found");
        }

        List<Appointment> appointments =
                appointmentRepository.findByDoctorId(doctorId);

        return appointmentMapper.toResponseList(appointments);
    }

    @Override
    public List<AppointmentResponse> getAppointmentsByPatient(Long patientId) {
        if (!patientRepository.existsById(patientId)) {
            throw new ResourceNotFoundException("Patient not found");
        }

        List<Appointment> appointments =
                appointmentRepository.findByPatientId(patientId);

        return appointmentMapper.toResponseList(appointments);
    }

    @Override
    public List<AppointmentResponse> getAppointmentsByDateRange(LocalDateTime start, LocalDateTime end) {
        List<Appointment> appointments =
                appointmentRepository.findByDateRange(start, end);

        return appointmentMapper.toResponseList(appointments);
    }

    @Override
    public AppointmentResponse updateAppointmentStatus(
            Long id,
            UpdateAppointmentStatusRequest request
    ) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        if (appointment.getStatus() == AppointmentStatus.COMPLETED) {
            throw new BusinessException("Completed appointment cannot be modified");
        }

        if (appointment.getStatus() == AppointmentStatus.CANCELLED) {
            throw new BusinessException("Cancelled appointment cannot be modified");
        }

        appointment.setStatus(request.getStatus());

        Appointment updated = appointmentRepository.save(appointment);

        return appointmentMapper.toResponse(updated);
    }

    @Override
    public void cancelAppointment(Long id) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        if (appointment.getStatus() == AppointmentStatus.COMPLETED) {
            throw new BusinessException("Completed appointment cannot be cancelled");
        }

        appointment.setStatus(AppointmentStatus.CANCELLED);

        appointmentRepository.save(appointment);
    }


}
