package com.fever.demo.infrastructure.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EventDTO {
    private String basePlanId;

    private String sellMode;

    private String organizerCompanyId;

    private String title;

    private List<PlanDTO> plan;
}
