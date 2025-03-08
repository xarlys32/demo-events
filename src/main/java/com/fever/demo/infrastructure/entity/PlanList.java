package com.fever.demo.infrastructure.entity;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@XmlRootElement(name = "planList")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlanList {

    @XmlAttribute(name = "version")
    private String version;

   /* @XmlAttribute(name = "xsi:noNamespaceSchemaLocation")
    private String xsiNoNamespaceSchemaLocation;*/

    @XmlElement(name = "output")
    private Output output;

}

@AllArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.FIELD)
class Output {

    @XmlElement(name = "base_plan")
    private List<BasePlan> basePlans;

}

@Data
@XmlAccessorType(XmlAccessType.FIELD)
class BasePlan {

    @XmlAttribute(name = "base_plan_id")
    private String basePlanId;

    @XmlAttribute(name = "sell_mode")
    private String sellMode;

    @XmlAttribute(name = "organizer_company_id")
    private String organizerCompanyId;

    @XmlAttribute(name = "title")
    private String title;

    @XmlElement(name = "plan")
    private List<Plan> plans;

}

@AllArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.FIELD)
class Plan {

    @XmlAttribute(name = "plan_start_date")
    private String planStartDate;

    @XmlAttribute(name = "plan_end_date")
    private String planEndDate;

    @XmlAttribute(name = "plan_id")
    private String planId;

    @XmlAttribute(name = "sell_from")
    private String sellFrom;

    @XmlAttribute(name = "sell_to")
    private String sellTo;

    @XmlAttribute(name = "sold_out")
    private String soldOut;

    @XmlElement(name = "zone")
    private List<Zone> zones;

}

@AllArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.FIELD)
class Zone {

    @XmlAttribute(name = "zone_id")
    private String zoneId;

    @XmlAttribute(name = "capacity")
    private String capacity;

    @XmlAttribute(name = "price")
    private String price;

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "numbered")
    private String numbered;

}

