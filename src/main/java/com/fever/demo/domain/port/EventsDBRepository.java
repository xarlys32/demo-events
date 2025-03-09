package com.fever.demo.domain.port;

import com.fever.demo.domain.model.EventDom;

import java.util.List;

public interface EventsDBRepository {
    List<EventDom> getAllEvents();
    void addNewEvents(List<EventDom> newEvents);
}
