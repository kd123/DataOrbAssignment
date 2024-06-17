package com.PPC.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class EmployeeEventTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeReferenceNumber;
    private String eventName;
    private String eventValue;
    private Integer eventDay;
    private Integer eventMonth;
    private Integer eventYear;
    private LocalDate eventDate;
    private String notes;

}
