package com.fever.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EventDom {
    private String basePlanId;

    private String sellMode;

    private String organizerCompanyId;

    private String title;

    private List<PlanDom> plans;
}
