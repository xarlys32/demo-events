package com.fever.demo.domain.usecases;

import com.fever.demo.domain.model.EventDom;
import com.fever.demo.domain.port.EventsDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventUsesCases {

    @Autowired
    private EventsDBRepository eventsDBRepository;

    /*public List<EventDom> findEventsFromDate(String startDate, String endDate) {

    }*/

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
}
