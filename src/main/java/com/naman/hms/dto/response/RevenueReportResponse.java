package com.naman.hms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class RevenueReportResponse {

    private BigDecimal totalRevenue;

}