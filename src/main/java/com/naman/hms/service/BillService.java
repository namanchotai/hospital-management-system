package com.naman.hms.service;

import com.naman.hms.dto.response.BillResponse;
import com.naman.hms.dto.response.RevenueReportResponse;
import com.naman.hms.enums.BillStatus;

import java.util.List;

public interface BillService {

    BillResponse getBillById(Long id);

    BillResponse getBillByAppointment(Long appointmentId);

    BillResponse processPayment(Long billId);

    List<BillResponse> getBillsByStatus(BillStatus status);

    RevenueReportResponse getRevenueReport();

}