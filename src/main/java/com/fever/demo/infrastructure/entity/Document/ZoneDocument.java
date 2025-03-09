package com.fever.demo.infrastructure.entity.Document;

import org.springframework.data.mongodb.core.mapping.Field;

public class ZoneDocument {
    @Field(name = "zone_id")
    private String zoneId;

    @Field(name = "capacity")
    private String capacity;

    @Field(name = "price")
    private String price;

    @Field(name = "name")
    private String name;

    @Field(name = "numbered")
    private String numbered;
}
