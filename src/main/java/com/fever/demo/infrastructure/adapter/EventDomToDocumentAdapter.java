package com.fever.demo.infrastructure.adapter;

import com.fever.demo.domain.model.EventDom;
import com.fever.demo.domain.model.PlanDom;
import com.fever.demo.domain.model.ZoneDom;
import com.fever.demo.infrastructure.entity.Document.BasePlanDocument;
import com.fever.demo.infrastructure.entity.Document.PlanDocument;
import com.fever.demo.infrastructure.entity.Document.ZoneDocument;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventDomToDocumentAdapter {
    public List<BasePlanDocument> toDocument(List<EventDom> eventList) {
        return eventList.stream().map(EventDomToDocumentAdapter::basePlanDomToDocument).toList();
    }

    private static BasePlanDocument basePlanDomToDocument(EventDom eventDom) {
        return new BasePlanDocument(eventDom.getBasePlanId(), eventDom.getSellMode(),
                eventDom.getOrganizerCompanyId(), eventDom.getTitle(), eventDom.getPlans().stream().map(EventDomToDocumentAdapter::planDomToDocument).toList());
    }

    private static PlanDocument planDomToDocument(PlanDom planDom) {
        return new PlanDocument(planDom.getPlanStartDate(),planDom.getPlanEndDate(), planDom.getPlanId(),
                planDom.getSellFrom(), planDom.getSellTo(), planDom.getSoldOut(),
                planDom.getZoneDoms().stream().map(EventDomToDocumentAdapter::zoneDomToDocument).toList());
    }

    private static ZoneDocument zoneDomToDocument(ZoneDom zoneDom) {
        return new ZoneDocument(zoneDom.getZoneId(), zoneDom.getCapacity(), zoneDom.getPrice(),
                zoneDom.getName(), zoneDom.getNumbered());
    }
}
