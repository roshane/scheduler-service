package com.aeon.scheduler.service.controller;

import com.aeon.scheduler.service.service.HealthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController {
    private final HealthService healthService;

    public SystemController(HealthService healthService) {
        this.healthService = healthService;
    }

    @GetMapping(value = "/health", produces = {"text/plain"})
    public String health() {
        return healthService.getHealth().toResponseString();
    }
}
