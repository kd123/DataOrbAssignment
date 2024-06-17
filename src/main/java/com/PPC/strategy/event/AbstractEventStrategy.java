package com.PPC.strategy.event;


import com.PPC.converter.RecordToEventTrackerConverter;
import com.PPC.entity.EmployeeEventTracker;

/**
 * Abstract class to handle all the common functions of the event
 */

public abstract class AbstractEventStrategy implements EventRecordProcessStrategy{


    private  final RecordToEventTrackerConverter recordToEventTrackerConverter;

    public AbstractEventStrategy(RecordToEventTrackerConverter recordToEventTrackerConverter) {
        this.recordToEventTrackerConverter = recordToEventTrackerConverter;
    }

    public EmployeeEventTracker process(String record){
        return recordToEventTrackerConverter.convert(record,null);
    }

}
