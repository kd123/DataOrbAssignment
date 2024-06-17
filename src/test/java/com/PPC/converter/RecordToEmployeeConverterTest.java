package com.PPC.converter;

import com.PPC.entity.Employee;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class RecordToEmployeeConverterTest {

    @InjectMocks
    RecordToEmployeeConverter recordToEmployeeConverter;

    @Test
    public void convert(){
        Employee employee = recordToEmployeeConverter.convert(null, any());
        assertNull(employee);
    }
}
