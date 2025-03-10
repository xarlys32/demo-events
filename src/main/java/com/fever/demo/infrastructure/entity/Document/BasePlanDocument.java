package com.fever.demo.infrastructure.entity.Document;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@AllArgsConstructor
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
    private List<PlanDocument> plan;

}
