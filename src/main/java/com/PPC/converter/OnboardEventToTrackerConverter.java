package com.PPC.converter;

import com.PPC.entity.EmployeeEventTracker;
import com.PPC.util.DateUtil;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class OnboardEventToTrackerConverter implements Converter<String, EmployeeEventTracker,EmployeeEventTracker>{

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
        existing.setEventName(records[5]);
        existing.setEventValue(records[6]);
        existing.setEventDate(DateUtil.convertStringToDate(records[7],DATE_FORMAT));
        String[] eventDate = records[6].split("-");
        existing.setEventMonth(Integer.valueOf(eventDate[0]));
        existing.setEventDay(Integer.valueOf(eventDate[1]));
        existing.setEventYear(Integer.valueOf(eventDate[2]));
        existing.setNotes(records[8]);
        return existing;
    }
}
