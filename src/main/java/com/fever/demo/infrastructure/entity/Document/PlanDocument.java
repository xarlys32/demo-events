package com.fever.demo.infrastructure.entity.Document;

import com.fever.demo.infrastructure.entity.Xml.Zone;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class PlanDocument {
    @Field(name = "plan_start_date")
    private String planStartDate;

    @Field(name = "plan_end_date")
    private String planEndDate;

    @Field(name = "plan_id")
    private String planId;

    @Field(name = "sell_from")
    private String sellFrom;

    @Field(name = "sell_to")
    private String sellTo;

    @Field(name = "sold_out")
    private String soldOut;

    @Field(name = "zone")
    private List<Zone> zones;
}
