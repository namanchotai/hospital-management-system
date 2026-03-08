package com.naman.hms.controller;

import com.naman.hms.common.response.ApiResponse;
import com.naman.hms.dto.response.BillResponse;
import com.naman.hms.dto.response.RevenueReportResponse;
import com.naman.hms.enums.BillStatus;
import com.naman.hms.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @GetMapping("/{id}")
    public ApiResponse<BillResponse> getBill(@PathVariable Long id) {

        return ApiResponse.<BillResponse>builder()
                .success(true)
                .message("Bill fetched successfully")
                .data(billService.getBillById(id))
                .build();
    }

    @GetMapping("/appointment/{appointmentId}")
    public ApiResponse<BillResponse> getBillByAppointment(@PathVariable Long appointmentId) {

        return ApiResponse.<BillResponse>builder()
                .success(true)
                .message("Bill fetched successfully")
                .data(billService.getBillByAppointment(appointmentId))
                .build();
    }

    @PutMapping("/{id}/payment")
    public ApiResponse<BillResponse> processPayment(@PathVariable Long id) {

        return ApiResponse.<BillResponse>builder()
                .success(true)
                .message("Payment processed successfully")
                .data(billService.processPayment(id))
                .build();
    }

    @GetMapping("/status/{status}")
    public ApiResponse<List<BillResponse>> getBillsByStatus(@PathVariable BillStatus status) {

        return ApiResponse.<List<BillResponse>>builder()
                .success(true)
                .message("Bills fetched successfully")
                .data(billService.getBillsByStatus(status))
                .build();
    }

    @GetMapping("/revenue-report")
    public ApiResponse<RevenueReportResponse> getRevenueReport() {

        return ApiResponse.<RevenueReportResponse>builder()
                .success(true)
                .message("Revenue report generated")
                .data(billService.getRevenueReport())
                .build();
    }
}