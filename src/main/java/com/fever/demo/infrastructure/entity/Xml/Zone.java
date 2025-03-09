package com.fever.demo.infrastructure.entity.Xml;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Zone {

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