package com.fever.demo.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Plan {
    private String planStartDate;

    private String planEndDate;

    private String planId;

    private String sellFrom;

    private String sellTo;

    private String soldOut;

    private List<Zone> zones;
}
