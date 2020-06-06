package com.aeon.scheduler.service.http.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class HealthResponse {
    private final String timeStamp;
    private final String version;
    private final String status;
    private final String activeProfile;

    public String toResponseString() {
        return new StringBuilder()
                .append("status: ").append(this.status)
                .append("\n")
                .append("version: ").append(this.version)
                .append("\n")
                .append("timestamp: ").append(this.timeStamp)
                .append("\n")
                .append("activeProfile: ").append(this.activeProfile)
                .toString();

    }
}
