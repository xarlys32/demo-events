package com.fever.demo.infrastructure.entity.Document;

import com.fever.demo.infrastructure.entity.Xml.Plan;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "eventsTest")
public class BasePlanDocument {

    @Field(name = "base_plan_id")
    private String basePlanId;

    @Field(name = "sell_mode")
    private String sellMode;

    @Field(name = "organizer_company_id")
    private String organizerCompanyId;

    @Field(name = "title")
    private String title;

    @Field(name = "plan")
    private List<Plan> plans;

}
