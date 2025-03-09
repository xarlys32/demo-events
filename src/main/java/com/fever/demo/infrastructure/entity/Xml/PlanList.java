package com.fever.demo.infrastructure.entity.Xml;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@XmlRootElement(name = "planList")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlanList {

    @XmlAttribute(name = "version")
    private String version;


    @XmlElement(name = "output")
    private Output output;

}



