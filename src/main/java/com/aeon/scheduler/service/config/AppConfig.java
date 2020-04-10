package com.aeon.scheduler.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:git.properties")
public class AppConfig {

    @Value("${postgres.db.host}")
    private String postgresHost;
    @Value("${postgres.db.port}")
    private int postgresPort;
    @Value("${postgres.db.user}")
    private String postgresUser;
    @Value("${postgres.db.password}")
    private String postgresPassword;

    public String getPostgresHost() {
        return postgresHost;
    }

    public int getPostgresPort() {
        return postgresPort;
    }

    public String getPostgresUser() {
        return postgresUser;
    }

    public String getPostgresPassword() {
        return postgresPassword;
    }
}
