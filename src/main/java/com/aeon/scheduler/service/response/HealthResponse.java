package com.aeon.scheduler.service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@ToString
@Getter
public class HealthResponse {
    private LocalDateTime timeStamp;
    private String version;
    private String status;

    public String toResponseString() {
        return new StringBuilder()
                .append("status: ").append(this.status)
                .append("\n")
                .append("version: ").append(this.version)
                .append("\n")
                .append("timestamp: ").append(this.timeStamp)
                .toString();

    }
}