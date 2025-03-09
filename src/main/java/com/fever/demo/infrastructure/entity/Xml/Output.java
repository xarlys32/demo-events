package com.fever.demo.infrastructure.entity.Xml;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Output {

    @XmlElement(name = "base_plan")
    private List<BasePlan> basePlans;

}