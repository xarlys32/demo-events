package com.fever.demo.infrastructure.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class EventsController {
    @GetMapping("/demo")
    public String Hello() {
        return "hey ho!";
    }
}
