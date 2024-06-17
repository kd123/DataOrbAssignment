package com.PPC.strategy.event;

import com.PPC.entity.EmployeeEventTracker;
import com.PPC.enums.Event;

public interface EventRecordProcessStrategy {
    EmployeeEventTracker process(String record);
    Event getEventName();
}
