package com.PPC.strategy.fileprocess;

import com.PPC.constants.ErrorConstant;
import com.PPC.converter.RecordToEmployeeConverter;
import com.PPC.entity.Employee;
import com.PPC.entity.EmployeeEventTracker;
import com.PPC.enums.Event;
import com.PPC.exception.InvalidRequestException;
import com.PPC.repository.EmployeeEventTrackerRepository;
import com.PPC.repository.EmployeeRepository;
import com.PPC.strategy.event.EventRecordHandler;
import com.PPC.strategy.event.EventRecordProcessStrategy;
import com.PPC.validator.EmployeeValidator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class TextFileProcessStrategy implements FileProcessStrategy{


    @Autowired
    private RecordToEmployeeConverter recordToEmployeeConverter;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EventRecordHandler eventRecordHandler;

    @Autowired
    private EmployeeEventTrackerRepository employeeEventTrackerRepository;



    @Override
    public void process(MultipartFile file) throws IOException {
        List<Employee> employeeList = new ArrayList<>();
        List<EmployeeEventTracker> employeeEventTrackers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line = reader.readLine();
            while(line!=null){
                Employee employee = recordToEmployeeConverter.convert(line, null);
                String filename = file.getOriginalFilename();
                if(StringUtils.isNotBlank(filename)){
                    String companyName = filename.split("\\.")[0];
                    employee.setCompanyName(companyName);
                }
                if(StringUtils.isNotBlank(employee.getEmployeeReferenceNumber())){
                    ErrorConstant errorConstant = EmployeeValidator.isFirstNameValid()
                            .and(EmployeeValidator.isDesignationValid()).apply(employee);
                    if(Objects.nonNull(errorConstant)){
                        throw new InvalidRequestException(errorConstant.getErrorCode(),errorConstant.getErrorMessage());
                    }
                }
                employeeList.add(employee);
                Event event = getEvent(line);
                EventRecordProcessStrategy eventHandlerStrategy = eventRecordHandler.getEventHandlerStrategy(event);
                EmployeeEventTracker employeeEventTracker = eventHandlerStrategy.process(line);
                employeeEventTrackers.add(employeeEventTracker);
                line = reader.readLine();
            }
        }
        if(CollectionUtils.isNotEmpty(employeeList)) {
            employeeRepository.saveAll(employeeList);
        }
        if(CollectionUtils.isNotEmpty(employeeEventTrackers)){
            employeeEventTrackerRepository.saveAll(employeeEventTrackers);
        }
    }

    @Override
    public String getFileType() {
        return MimeTypeUtils.TEXT_PLAIN_VALUE;
    }

    private Event getEvent(String record){
        String[] employeeData = record.split(",");
        String eventName;
        if(employeeData.length==9){
            eventName = employeeData[5];
        }
        else{
            eventName = employeeData[2];
        }
        if(EnumUtils.isValidEnum(Event.class,eventName)){
            return EnumUtils.getEnum(Event.class,eventName);
        }
        return null;
    }
}
