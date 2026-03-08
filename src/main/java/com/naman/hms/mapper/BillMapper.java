package com.naman.hms.mapper;

import com.naman.hms.dto.response.BillResponse;
import com.naman.hms.entity.Bill;

public class BillMapper {

    public static BillResponse toResponse(Bill bill) {

        BillResponse response = new BillResponse();

        response.setId(bill.getId());
        response.setAmount(bill.getAmount());
        response.setStatus(bill.getStatus());

        response.setAppointmentId(bill.getAppointment().getId());
        response.setPatientId(bill.getAppointment().getPatient().getId());
        response.setDoctorId(bill.getAppointment().getDoctor().getId());

        return response;
    }

}