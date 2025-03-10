package com.fever.demo.domain.usecases;

import com.fever.demo.config.Constants;
import com.fever.demo.domain.model.EventDom;
import com.fever.demo.domain.port.EventsDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventUsesCases {

    @Autowired
    private EventsDBRepository eventsDBRepository;

    public List<EventDom> findEventsFromDate(String startDate, String endDate) {
        if(validateDates(startDate, endDate)) {
            return getEventFromRangeDates(startDate, endDate);
        }
        return new ArrayList<>();
    }

    public void getProcessedEvent(List<EventDom> eventList) {
        List<EventDom> newEvents = findNewEventsFromXml(eventList);
        if (!newEvents.isEmpty()) {
            addNewEventsToDb(newEvents);
        }
    }

    private List<EventDom> findNewEventsFromXml (List<EventDom> eventsFromXml) {
        List<EventDom> eventsFromDb =  eventsDBRepository.getAllEvents();
        return eventsFromXml.stream().filter(eventXml -> eventsFromDb.stream().noneMatch(
                eventDb-> eventDb.getBasePlanId().equals(eventXml.getBasePlanId()))).collect(Collectors.toList());
    }
    
    private void addNewEventsToDb(List<EventDom> newEvents) {
        eventsDBRepository.addNewEvents(newEvents);
    }

    private boolean validateDates(String startDate, String endDate) {
        if(!isValidDateFormat(startDate) || !isValidDateFormat(endDate)) {
            return false;
        }
        return checkIfStartDateIsPreviousToEnd(startDate, endDate);
    }

    private boolean checkIfStartDateIsPreviousToEnd(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);
        return !end.isBefore(start);
    }

    private boolean isValidDateFormat(String date) {
        return date != null && date.matches(Constants.DATE_FORMAT);
    }

    private List<EventDom> getEventFromRangeDates(String startDate, String endDate) {

        return eventsDBRepository.getEventsFromTimeRange(addHours(startDate,true), addHours(endDate, false));
    }

    private String addHours(String date, boolean isStart) {
        LocalDate dateWithHours = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        return isStart ? dateWithHours.atTime(0,0,1).toString() : dateWithHours.atTime(23,59,59).toString();
    }
}
