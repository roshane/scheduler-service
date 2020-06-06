package com.aeon.scheduler.service.service;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import com.aeon.scheduler.service.model.quartz.LoggingJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class QuartzService {
    private final Logger logger = LoggerFactory.getLogger(QuartzService.class);
    private final Scheduler scheduler;

    public QuartzService(Scheduler scheduler) {
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

    public Optional<String> createPrintJob() {
        long timestamp = System.currentTimeMillis();
        String jobId = LoggingJob.class.getSimpleName() + "_" + timestamp;
        String triggerId = LoggingJob.class.getSimpleName() + "_" + timestamp;
        LocalDateTime now = LocalDateTime.now();
        JobDetail job = newJob(LoggingJob.class)
                .withIdentity(new JobKey(jobId, LoggingJob.class.getSimpleName()))
                .usingJobData("createdAt", now.toString())
                .build();


        Trigger trigger = newTrigger().forJob(job)
                .withIdentity(new TriggerKey(triggerId, LoggingJob.class.getSimpleName()))
                .startAt(Date.from(Instant.now().plus(1, ChronoUnit.SECONDS)))
                .build();
        try {
            scheduler.scheduleJob(job, trigger);
            return Optional.of(String.format("Trigger[%s]\nJob[%s]", triggerId, jobId));
        } catch (SchedulerException ex) {
            logger.error("Error scheduling loggingJob", ex);
        }
        return Optional.empty();
    }
}
