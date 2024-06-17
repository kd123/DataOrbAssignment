package com.PPC.strategy.event;

import com.PPC.constants.ErrorConstant;
import com.PPC.enums.Event;
import com.PPC.exception.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Class which pre-populates the required services for strategy implementation
 */

@Service
@Slf4j
public class EventRecordHandler {

    private Map<Event,EventRecordProcessStrategy> eventEventRecordProcessStrategyMap;

    @Autowired
    public EventRecordHandler(Set<EventRecordProcessStrategy> eventRecordProcessStrategies) {
        eventEventRecordProcessStrategyMap = new HashMap<>();
        eventRecordProcessStrategies.forEach(each -> eventEventRecordProcessStrategyMap.put(each.getEventName(),each));
    }

    public EventRecordProcessStrategy getEventHandlerStrategy(Event event){
        if(Objects.isNull(eventEventRecordProcessStrategyMap.get(event))){
            log.error("No service found for event {}",event);
            throw new InvalidRequestException(ErrorConstant.BAD_REQUEST_INVALID_EVENT.getErrorCode(),
                    ErrorConstant.BAD_REQUEST_INVALID_EVENT.getErrorMessage());
        }
        return eventEventRecordProcessStrategyMap.get(event);
    }


}
