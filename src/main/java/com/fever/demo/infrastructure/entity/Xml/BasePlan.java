package com.fever.demo.infrastructure.entity.Xml;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class BasePlan {

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

