package com.aeon.scheduler.service.service;

import com.aeon.scheduler.service.http.response.HealthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HealthService {
    @Value("${last.commit}")
    private String lastCommitHash;
    @Value("${last.timestamp}")
    private String lastCommitTimestamp;
    @Value("${spring.profiles.active}")
    private String activeProfile;

    private final String healthy = "healthy";
    private final String unhealthy = "unhealthy";

    //TODO actual system check
    public HealthResponse getHealth() {
        return new HealthResponse(lastCommitTimestamp, lastCommitHash, getSystemHealth(), activeProfile);
    }

    private String getSystemHealth() {
        return healthy;
    }
}
