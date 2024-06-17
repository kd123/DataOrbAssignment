package com.PPC.controller;

import com.PPC.dto.ApiResponse;
import com.PPC.dto.EmployeeFinancialReport;
import com.PPC.dto.EmployeeMonthlyReport;
import com.PPC.service.EmployeeDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee-dashboard")
public class EmployeeDashboardController {

    @Autowired
    private EmployeeDashboardService employeeDashboardService;

    @GetMapping("/employee-count")
    public ResponseEntity<ApiResponse<Integer>> getEmployeeCount(@RequestParam String companyName){
        return new ResponseEntity<>(employeeDashboardService.getEmployeeCount(companyName), HttpStatus.OK);
    }

    @GetMapping("/monthly-report")
    public ResponseEntity<ApiResponse<EmployeeMonthlyReport>> getMonthlySalaryReport(){
        return new ResponseEntity<>(employeeDashboardService.getMonthlySalaryReport(),HttpStatus.OK);
    }

    @GetMapping("/monthly/amount")
    public ResponseEntity<ApiResponse<EmployeeMonthlyReport>> getAmountReleased(){
        return new ResponseEntity<>(employeeDashboardService.getAmountReleased(),HttpStatus.OK);
    }

    @GetMapping("/yearly-report")
    public ResponseEntity<ApiResponse<List<EmployeeFinancialReport>>> getFinancialReport(@RequestParam Integer year){
        return new ResponseEntity<>(employeeDashboardService.getFinancialReport(year),HttpStatus.OK);
    }

}
