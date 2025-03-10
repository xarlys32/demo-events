package com.fever.demo.infrastructure.repository;

import com.fever.demo.domain.model.EventDom;
import com.fever.demo.domain.port.EventsDBRepository;
import com.fever.demo.infrastructure.adapter.EventDocumentToDomAdapter;
import com.fever.demo.infrastructure.adapter.EventDomToDocumentAdapter;
import com.fever.demo.infrastructure.entity.Document.BasePlanDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventsDBRepositroryImpl implements EventsDBRepository {

    @Autowired
    private EventsMongoRepository eventsMongoRepository;

    @Autowired
    private EventDocumentToDomAdapter eventDocumentToDomAdapter;

    @Autowired
    private EventDomToDocumentAdapter eventDomToDocumentAdapter;

    public List<EventDom> getAllEvents() {
        List<BasePlanDocument>  basePlanList = eventsMongoRepository.findAll();
        return eventDocumentToDomAdapter.toDom(basePlanList);
    }

    public void addNewEvents(List<EventDom> newEvents) {
        List<BasePlanDocument> newEventsDocuments = eventDomToDocumentAdapter.toDocument(newEvents);
        eventsMongoRepository.saveAll(newEventsDocuments);
    }

    public List<EventDom> getEventsFromTimeRange(String start, String end) {
        List<BasePlanDocument> eventsDocument = eventsMongoRepository.findByPlanBaseDates(start, end);
        return eventDocumentToDomAdapter.toDom(eventsDocument);
    }
}
