package com.PPC.converter;

import com.PPC.dto.EmployeeFinancialReport;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class EventToFinancialYearConverterTest {

    @InjectMocks
    EventToFinancialYearConverter eventToFinancialYearConverter;

    @Test
    void convert(){
        EmployeeFinancialReport employeeFinancialReport = eventToFinancialYearConverter.convert(null, any());
        assertNull(employeeFinancialReport);
    }

}
