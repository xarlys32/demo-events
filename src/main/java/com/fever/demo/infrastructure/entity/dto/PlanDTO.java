package com.fever.demo.infrastructure.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PlanDTO {
    private String planStartDate;

    private String planEndDate;

    private String planId;

    private String sellFrom;

    private String sellTo;

    private String soldOut;

    private List<ZoneDTO> zone;
}
