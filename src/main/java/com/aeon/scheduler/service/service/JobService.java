package com.aeon.scheduler.service.service;

import java.util.List;
import java.util.Optional;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    private final Logger logger = LoggerFactory.getLogger(JobService.class);
    private final Scheduler scheduler;

    public JobService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Optional<List<String>> getJobGroupNames() {
        try {
            return Optional.of(scheduler.getJobGroupNames());
        } catch (SchedulerException ex) {
            logger.error("Error getJobGroupNames", ex);
        }
        return Optional.empty();
    }
}
