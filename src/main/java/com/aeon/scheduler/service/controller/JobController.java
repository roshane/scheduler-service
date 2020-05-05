package com.aeon.scheduler.service.controller;

import java.util.Collections;
import java.util.List;

import com.aeon.scheduler.service.service.JobService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping(value = "/jobs", produces = {"application/json"})
    public List<String> jobs() {
        return jobService.getJobGroupNames().orElse(Collections.emptyList());
    }
}
