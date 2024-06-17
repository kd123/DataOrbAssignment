package com.PPC.converter;

import com.PPC.entity.EmployeeEventTracker;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class OnboardEventToTrackerConverterTest {

    @InjectMocks
    OnboardEventToTrackerConverter onboardEventToTrackerConverter;

    @Test
    public void convert(){
        EmployeeEventTracker employeeEventTracker = onboardEventToTrackerConverter.convert(null, any());
        assertNull(employeeEventTracker);
    }
}
