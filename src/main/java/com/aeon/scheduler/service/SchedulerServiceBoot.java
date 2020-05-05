package com.aeon.scheduler.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SchedulerServiceBoot {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SchedulerServiceBoot.class, args);
    }

}
