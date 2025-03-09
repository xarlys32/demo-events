package com.fever.demo.infrastructure.entity.Xml;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Plan {

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