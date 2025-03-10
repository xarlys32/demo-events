package com.fever.demo.infrastructure.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ZoneDTO {
    private String zoneId;

    private String capacity;

    private String price;

    private String name;

    private String numbered;
}
