package com.PPC.service;

import com.PPC.dto.ApiResponse;
import com.PPC.dto.EmployeeFinancialReport;
import com.PPC.dto.EmployeeMonthlyReport;

import java.util.List;

public interface EmployeeDashboardService {
    ApiResponse<Integer> getEmployeeCount(String companyName);
    ApiResponse<EmployeeMonthlyReport> getMonthlySalaryReport();
    ApiResponse<EmployeeMonthlyReport> getAmountReleased();
    ApiResponse<List<EmployeeFinancialReport>> getFinancialReport(Integer year);

}
