package com.fever.demo.domain.usecases;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.fever.demo.config.Constants;
import com.fever.demo.domain.common.DateHelper;
import com.fever.demo.domain.model.EventDom;
import com.fever.demo.domain.port.EventsDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventUsesCases {

    @Autowired
    private EventsDBRepository eventsDBRepository;

    public List<EventDom> findEventsFromDate(String startDate, String endDate) {
        try {
            if (DateHelper.validateDates(startDate, endDate)) {
                return getEventFromRangeDates(startDate, endDate);
            }
        } catch (DateTimeParseException ex) {
            System.err.println(ex);
        }
        return new ArrayList<>();
    }

    public void getProcessedEvent(List<EventDom> eventList) {
        try {
        List<EventDom> newEvents = findNewEventsFromXml(eventList);
        if (!newEvents.isEmpty()) {
            addNewEventsToDb(newEvents);
        }
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    private List<EventDom> findNewEventsFromXml (List<EventDom> eventsFromXml) throws Exception{
        List<EventDom> eventsFromDb =  eventsDBRepository.getAllEvents();
        return eventsFromXml.stream().filter(eventXml -> eventsFromDb.stream().noneMatch(
                eventDb-> eventDb.getBasePlanId().equals(eventXml.getBasePlanId()))).collect(Collectors.toList());
    }
    
    private void addNewEventsToDb(List<EventDom> newEvents) {
        eventsDBRepository.addNewEvents(newEvents);
    }


    private List<EventDom> getEventFromRangeDates(String startDate, String endDate) throws DateTimeParseException {
        return eventsDBRepository.getEventsFromTimeRange(DateHelper.addHours(startDate,true), DateHelper.addHours(endDate, false));
    }

}
