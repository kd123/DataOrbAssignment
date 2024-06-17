package com.PPC.dto;

import lombok.Builder;

@Builder
public class EmployeeMonthlyReport {
    private Integer month;
    private Long totalSalary;
    private Integer employeeCount;
}
