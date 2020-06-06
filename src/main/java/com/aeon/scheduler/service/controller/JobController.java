package com.aeon.scheduler.service.controller;

import java.util.Collections;
import java.util.List;

import com.aeon.scheduler.service.service.QuartzService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {
    private final QuartzService quartzService;

    public JobController(QuartzService quartzService) {
        this.quartzService = quartzService;
    }

    @GetMapping(value = "/jobs", produces = {"application/json"})
    public List<String> jobs() {
        return quartzService.getJobGroupNames().orElse(Collections.emptyList());
    }

    @PostMapping(value = "/jobs", produces = {"text/plain"})
    public String createJob() throws Exception {
        return quartzService.createPrintJob().orElseThrow(() -> new Exception("Error scheduling job"));
    }
}
