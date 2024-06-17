package com.PPC.service;

import com.PPC.converter.EventToFinancialYearConverter;
import com.PPC.dto.ApiResponse;
import com.PPC.dto.EmployeeFinancialReport;
import com.PPC.dto.EmployeeMonthlyReport;
import com.PPC.entity.Employee;
import com.PPC.entity.EmployeeEventTracker;
import com.PPC.enums.Event;
import com.PPC.exception.InvalidRequestException;
import com.PPC.repository.EmployeeEventTrackerRepository;
import com.PPC.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.PPC.constants.ErrorConstant.BAD_REQUEST_EMPTY_COMPANY_NAME;

@Service
@Slf4j
public class EmployeeDashboardServiceImpl implements EmployeeDashboardService{

    @Autowired
    private EmployeeEventTrackerRepository employeeEventTrackerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EventToFinancialYearConverter eventToFinancialYearConverter;

    public ApiResponse<Integer> getEmployeeCount(String companyName){
        if(StringUtils.isBlank(companyName)){
            throw new InvalidRequestException(BAD_REQUEST_EMPTY_COMPANY_NAME.getErrorCode(),
                    BAD_REQUEST_EMPTY_COMPANY_NAME.getErrorMessage());
        }
        List<Employee> employeeList = employeeRepository.findByCompanyName(companyName);
        ApiResponse<Integer> response = new ApiResponse<>();
        return response.buildSuccessResponse(HttpStatus.OK.value(),"Success",employeeList.size());
    }

    public ApiResponse<EmployeeMonthlyReport> getMonthlySalaryReport(){
        List<String> events = List.of(Event.SALARY.name());
        EmployeeMonthlyReport employeeMonthlyReport = getEmployeeReport(events);
        ApiResponse<EmployeeMonthlyReport> response = new ApiResponse<>();
        response.buildSuccessResponse(HttpStatus.OK.value(), "Monthly report generated",employeeMonthlyReport);
        return response;
    }

    public ApiResponse<EmployeeMonthlyReport> getAmountReleased(){
        List<String> events = Arrays.asList(Event.SALARY.name(),Event.REIMBURSEMENT.name(),Event.BONUS.name());
        EmployeeMonthlyReport employeeMonthlyReport = getEmployeeReport(events);
        ApiResponse<EmployeeMonthlyReport> response = new ApiResponse<>();
        response.buildSuccessResponse(HttpStatus.OK.value(), "Amount release report generated",employeeMonthlyReport);
        return response;
    }

    public ApiResponse<List<EmployeeFinancialReport>> getFinancialReport(Integer year){
        List<EmployeeEventTracker> employeeEventTrackers = employeeEventTrackerRepository.findByEventYear(year);
        List<EmployeeFinancialReport> employeeFinancialReports = new ArrayList<>();
        for(EmployeeEventTracker each : employeeEventTrackers){
            EmployeeFinancialReport employeeFinancialReport = eventToFinancialYearConverter.convert(each, null);
            employeeFinancialReports.add(employeeFinancialReport);
        }
        ApiResponse<List<EmployeeFinancialReport>> response = new ApiResponse<>();
        return response.buildSuccessResponse(HttpStatus.OK.value(), "Financial report generated",employeeFinancialReports);
    }


    private EmployeeMonthlyReport getEmployeeReport(List<String> events){
        EmployeeMonthlyReport employeeMonthlyReport = null;
        List<EmployeeEventTracker> employeeEventTrackers = employeeEventTrackerRepository.findByEventNameIn(events);
        Map<Integer, List<EmployeeEventTracker>> trackersByMonth = employeeEventTrackers.stream()
                .collect(Collectors.groupingBy(EmployeeEventTracker::getEventMonth));
        for(Map.Entry<Integer, List<EmployeeEventTracker>> each : trackersByMonth.entrySet()){
            Optional<Long> payOptional = each.getValue().stream().map(pay -> Long.valueOf(pay.getEventValue())).reduce(Long::sum);
            if(payOptional.isPresent()) {
                employeeMonthlyReport = EmployeeMonthlyReport.builder().month(each.getKey())
                        .employeeCount(each.getValue().size()).totalSalary(payOptional.get()).build();
            }
        }
        return employeeMonthlyReport;
    }

}
