package com.fever.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ZoneDom {
    private String zoneId;

    private String capacity;

    private String price;

    private String name;

    private String numbered;
}
