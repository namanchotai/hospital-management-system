package com.naman.hms.service.impl;

import com.naman.hms.dto.response.BillResponse;
import com.naman.hms.dto.response.RevenueReportResponse;
import com.naman.hms.entity.Bill;
import com.naman.hms.enums.BillStatus;
import com.naman.hms.exception.BusinessException;
import com.naman.hms.exception.ResourceNotFoundException;
import com.naman.hms.mapper.BillMapper;
import com.naman.hms.repository.BillRepository;
import com.naman.hms.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    @Transactional(readOnly = true)
    @Override
    public BillResponse getBillById(Long id) {

        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Bill not found"));

        return BillMapper.toResponse(bill);
    }

    @Override
    @Transactional(readOnly = true)
    public BillResponse getBillByAppointment(Long appointmentId) {

        Bill bill = billRepository.findByAppointmentId(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found"));

        return BillMapper.toResponse(bill);
    }

    @Transactional
    @Override
    public BillResponse processPayment(Long billId) {

        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found"));

        if (bill.getStatus() == BillStatus.PAID) {
            throw new BusinessException("Bill already paid");
        }

        bill.setStatus(BillStatus.PAID);

        billRepository.save(bill);

        return BillMapper.toResponse(bill);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BillResponse> getBillsByStatus(BillStatus status) {

        return billRepository.findByStatus(status)
                .stream()
                .map(BillMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RevenueReportResponse getRevenueReport() {

        List<Bill> paidBills = billRepository.findByStatus(BillStatus.PAID);

        BigDecimal revenue = paidBills.stream()
                .map(Bill::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new RevenueReportResponse(revenue);
    }
}