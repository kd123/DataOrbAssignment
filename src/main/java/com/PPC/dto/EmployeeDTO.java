package com.PPC.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDTO {
    private int sequenceNo;
    private String empId;
    private String empFName;
    private String empLName;
    private String designation;
    private String eventValue;

    private String value;
    private LocalDate eventDate;
    private String notes;
}
