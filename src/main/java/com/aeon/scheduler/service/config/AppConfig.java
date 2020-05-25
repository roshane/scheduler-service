package com.aeon.scheduler.service.config;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

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
    @Value("${postgres.db.name}")
    private String postgresDbName;

    private String getPostgresUrl() {
        return String.format("jdbc:postgresql://%s:%d/%s", postgresHost, postgresPort, postgresDbName);
    }

    @Profile("prod")
    @Bean(name = "datasource")
    public DataSource defaultDatasource() {
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(getPostgresUrl());
        hikariConfig.setUsername(postgresUser);
        hikariConfig.setPassword(postgresPassword);
        return new HikariDataSource(hikariConfig);
    }



    @Profile("dev")
    @Bean(name = "datasource")
    public DataSource devDatasource() {
        return new DriverManagerDataSource(getPostgresUrl(), postgresUser, postgresPassword);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
