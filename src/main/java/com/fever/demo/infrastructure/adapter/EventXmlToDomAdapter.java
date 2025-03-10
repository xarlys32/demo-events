package com.fever.demo.infrastructure.adapter;

import com.fever.demo.domain.model.EventDom;
import com.fever.demo.domain.model.PlanDom;
import com.fever.demo.domain.model.ZoneDom;
import com.fever.demo.infrastructure.entity.Xml.BasePlan;
import com.fever.demo.infrastructure.entity.Xml.Plan;
import com.fever.demo.infrastructure.entity.Xml.PlanList;
import com.fever.demo.infrastructure.entity.Xml.Zone;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventXmlToDomAdapter {
    public List<EventDom> toDom(PlanList planList) {
        return planList.getOutput().getBasePlan().stream().map(EventXmlToDomAdapter::basePlanXmlToDom).toList();
    }

    private static EventDom basePlanXmlToDom(BasePlan basePlanXml) {
        return new EventDom(basePlanXml.getBasePlanId(), basePlanXml.getSellMode(),
                basePlanXml.getOrganizerCompanyId(), basePlanXml.getTitle(), basePlanXml.getPlan().stream().map(EventXmlToDomAdapter::planXmlToDom).toList());
    }

    private static PlanDom planXmlToDom(Plan planXml) {
        return new PlanDom(planXml.getPlanStartDate(),planXml.getPlanEndDate(), planXml.getPlanId(),
                planXml.getSellFrom(), planXml.getSellTo(), planXml.getSoldOut(), planXml.getZones().stream().map(EventXmlToDomAdapter::zoneXmlToDom).toList());
    }

    private static ZoneDom zoneXmlToDom(Zone zoneXml) {
        return new ZoneDom(zoneXml.getZoneId(), zoneXml.getCapacity(), zoneXml.getPrice(),zoneXml.getName(), zoneXml.getNumbered());
    }
}
