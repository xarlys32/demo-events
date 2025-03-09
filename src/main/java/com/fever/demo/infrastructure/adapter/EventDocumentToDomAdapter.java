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
public class EventDocumentToDomAdapter {
    public List<EventDom> toDom(List<BasePlanDocument> basePlanList) {
        return basePlanList.stream().map(EventDocumentToDomAdapter::basePlanDocumentToDom).toList();
    }

    private static EventDom basePlanDocumentToDom(BasePlanDocument basePlanDocument) {
        return new EventDom(basePlanDocument.getBasePlanId(), basePlanDocument.getSellMode(),
                basePlanDocument.getOrganizerCompanyId(), basePlanDocument.getTitle(),
                basePlanDocument.getPlans().stream().map(EventDocumentToDomAdapter::planDocumentToDom).toList());
    }

    private static PlanDom planDocumentToDom(PlanDocument planDocument) {
        return new PlanDom(planDocument.getPlanStartDate(),planDocument.getPlanEndDate(), planDocument.getPlanId(),
                planDocument.getSellFrom(), planDocument.getSellTo(), planDocument.getSoldOut(),
                planDocument.getZones().stream().map(EventDocumentToDomAdapter::zoneDocumentToDom).toList());
    }

    private static ZoneDom zoneDocumentToDom(ZoneDocument zoneDocument) {
        return new ZoneDom(zoneDocument.getZoneId(), zoneDocument.getCapacity(),
                zoneDocument.getPrice(),zoneDocument.getName(), zoneDocument.getNumbered());
    }
}
