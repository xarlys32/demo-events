package com.fever.demo.infrastructure.adapter;

import com.fever.demo.domain.model.EventDom;
import com.fever.demo.domain.model.PlanDom;
import com.fever.demo.domain.model.ZoneDom;
import com.fever.demo.infrastructure.entity.dto.EventDTO;
import com.fever.demo.infrastructure.entity.dto.PlanDTO;
import com.fever.demo.infrastructure.entity.dto.ZoneDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventDomToDTO {
    public List<EventDTO> toDTO(List<EventDom> eventList) {
        return eventList.stream().map(EventDomToDTO::eventDomToDTO).toList();
    }

    private static EventDTO eventDomToDTO(EventDom eventDom) {
        return new EventDTO(eventDom.getBasePlanId(), eventDom.getSellMode(),
                eventDom.getOrganizerCompanyId(), eventDom.getTitle(), eventDom.getPlan().stream().map(EventDomToDTO::planDomToDTO).toList());
    }

    private static PlanDTO planDomToDTO(PlanDom planDom) {
        return new PlanDTO(planDom.getPlanStartDate(),planDom.getPlanEndDate(), planDom.getPlanId(),
                planDom.getSellFrom(), planDom.getSellTo(), planDom.getSoldOut(),
                planDom.getZoneDom().stream().map(EventDomToDTO::zoneDomToDTO).toList());
    }

    private static ZoneDTO zoneDomToDTO(ZoneDom zoneDom) {
        return new ZoneDTO(zoneDom.getZoneId(), zoneDom.getCapacity(), zoneDom.getPrice(),
                zoneDom.getName(), zoneDom.getNumbered());
    }
}
