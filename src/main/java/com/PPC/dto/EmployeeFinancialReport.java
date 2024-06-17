package com.PPC.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
public class EmployeeFinancialReport {
    private String event;
    private String employeeId;
    private LocalDate eventDate;
    private String value;
}
