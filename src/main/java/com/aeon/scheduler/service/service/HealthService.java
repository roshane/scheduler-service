package com.aeon.scheduler.service.service;

import com.aeon.scheduler.service.response.HealthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HealthService {
    @Value("${last.commit}")
    private String lastCommitHash;

    @Value("${last.timestamp}")
    private String lastCommitTimestamp;

    private final String healthy = "healthy";
    private final String unhealthy = "unhealthy";

    //TODO actual system check
    public HealthResponse getHealth() {
        return new HealthResponse(lastCommitTimestamp, lastCommitHash, getSystemHealth());
    }

    private String getSystemHealth() {
        return healthy;
    }
}
