package com.PPC.converter;

import com.PPC.dto.EmployeeFinancialReport;
import com.PPC.entity.EmployeeEventTracker;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EventToFinancialYearConverter implements Converter<EmployeeEventTracker, EmployeeFinancialReport,EmployeeFinancialReport>{
    @Override
    public EmployeeFinancialReport convert(EmployeeEventTracker input, EmployeeFinancialReport existing) {
        if(Objects.isNull(input)) {
            return null;
        }
        if(Objects.isNull(existing)){
            existing = new EmployeeFinancialReport();
        }
        existing.setEvent(input.getEventName());
        existing.setEventDate(input.getEventDate());
        existing.setValue(input.getEventValue());
        existing.setEmployeeId(input.getEmployeeReferenceNumber());
        return existing;
    }
}
