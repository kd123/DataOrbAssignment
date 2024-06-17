package com.PPC.strategy.event;

import com.PPC.converter.OnboardEventToTrackerConverter;
import com.PPC.entity.EmployeeEventTracker;
import com.PPC.enums.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OnboardEventStrategy implements EventRecordProcessStrategy{

    @Autowired
    private OnboardEventToTrackerConverter onboardEventToTrackerConverter;


    @Override
    public EmployeeEventTracker process(String record) {
        return onboardEventToTrackerConverter.convert(record,null);
    }

    @Override
    public Event getEventName() {
        return Event.ONBOARD;
    }
}
