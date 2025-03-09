package com.fever.demo.infrastructure.entity.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
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
