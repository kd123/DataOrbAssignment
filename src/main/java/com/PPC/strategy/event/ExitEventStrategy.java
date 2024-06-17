package com.PPC.strategy.event;

import com.PPC.converter.RecordToEventTrackerConverter;
import com.PPC.entity.EmployeeEventTracker;
import com.PPC.enums.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExitEventStrategy extends AbstractEventStrategy{

    @Autowired
    public ExitEventStrategy(RecordToEventTrackerConverter recordToEventTrackerConverter) {
        super(recordToEventTrackerConverter);
    }


    @Override
    public Event getEventName() {
        return Event.EXIT;
    }
}
