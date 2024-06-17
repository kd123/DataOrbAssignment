package com.PPC.converter;

import com.PPC.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RecordToEmployeeConverter implements Converter<String, Employee,Employee> {

    @Override
    public Employee convert(String input, Employee existing) {
        if(Objects.isNull(input)) {
            return null;
        }
        if(Objects.isNull(existing)){
            existing = new Employee();
        }
        String[] employeeRecord = input.split(",");
        if(employeeRecord.length==9) {
            existing.setEmployeeReferenceNumber(employeeRecord[1]);
            existing.setFirstName(employeeRecord[2]);
            existing.setLastName(employeeRecord[3]);
            existing.setDesignation(employeeRecord[4]);
            existing.setActive(true);
        }
        return existing;

    }
}
