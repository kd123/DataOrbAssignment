package com.PPC.converter;

import com.PPC.entity.EmployeeEventTracker;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.PPC.util.DateUtil.convertStringToDate;

@Component
public class RecordToEventTrackerConverter implements Converter<String, EmployeeEventTracker,EmployeeEventTracker>{

    String DATE_FORMAT = "dd-MM-yyyy";

    @Override
    public EmployeeEventTracker convert(String input, EmployeeEventTracker existing) {
        if(Objects.isNull(input)) {
            return null;
        }
        if(Objects.isNull(existing)){
            existing = new EmployeeEventTracker();
        }
        String[] records = input.split(",");
        existing.setEmployeeReferenceNumber(records[1]);
        existing.setEventName(records[2]);
        existing.setEventValue(records[3]);
        existing.setEventDate(convertStringToDate(records[4],DATE_FORMAT));
        existing.setNotes(records[5]);
        String[] eventDate = records[4].split("-");
        existing.setEventMonth(Integer.valueOf(eventDate[0]));
        existing.setEventDay(Integer.valueOf(eventDate[1]));
        existing.setEventYear(Integer.valueOf(eventDate[2]));
        return existing;
    }
}
