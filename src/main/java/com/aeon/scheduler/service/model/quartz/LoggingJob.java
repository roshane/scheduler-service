package com.aeon.scheduler.service.model.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingJob implements Job {
    private final Logger logger = LoggerFactory.getLogger(LoggingJob.class);

    @Override
    public void execute(JobExecutionContext context) {
        logger.info("executing tasks {}", context.getJobDetail());
    }
}
