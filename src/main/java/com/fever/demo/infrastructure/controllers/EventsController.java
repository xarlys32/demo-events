package com.fever.demo.infrastructure.controllers;

import com.fever.demo.domain.model.EventDom;
import com.fever.demo.domain.usecases.EventUsesCases;
import com.fever.demo.infrastructure.adapter.EventDomToDTO;
import com.fever.demo.infrastructure.entity.dto.EventDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class EventsController {
    @Autowired
    private EventUsesCases eventUsesCases;

    @Autowired
    private EventDomToDTO eventDomToDTO;

    @GetMapping("/events")
    @Operation(summary = "Get event from a range of dates with format yyyy-MM-dd")
    public ResponseEntity<List<EventDTO>> getEventsByRangeOfDates(@RequestParam String startDate, @RequestParam String endDate) {
        List<EventDom> eventDomList = eventUsesCases.findEventsFromDate(startDate, endDate);
        return new ResponseEntity<>(eventDomToDTO.toDTO(eventDomList), HttpStatus.OK);
    }
}
