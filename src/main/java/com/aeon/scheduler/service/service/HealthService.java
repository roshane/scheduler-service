package com.aeon.scheduler.service.service;

import com.aeon.scheduler.service.response.HealthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HealthService {

    @Value("${project.version}")
    private String projectVersion;

    private final String healthy = "healthy";
    private final String unhealthy = "unhealthy";

    //TODO actual system check
    public HealthResponse getHealth() {
        return new HealthResponse(LocalDateTime.now(), projectVersion, healthy);
    }
}
