package com.aeon.scheduler.service.containers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.rnorth.ducttape.unreliables.Unreliables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.wait.strategy.AbstractWaitStrategy;

public class PostgresContainerWaitStrategy extends AbstractWaitStrategy {

    private int times = 1;
    private static final Logger logger = LoggerFactory.getLogger(PostgresContainerWaitStrategy.class);
    private final String username;
    private final String password;
    private final String url;
    private final Properties dbProps;

    public PostgresContainerWaitStrategy(String username, String password, String url) {
        this.username = username;
        this.password = password;
        this.url = url;
        dbProps = new Properties();
        dbProps.setProperty("user", this.username);
        dbProps.setProperty("password", this.password);
        dbProps.setProperty("loginTimeout", String.valueOf(startupTimeout.getSeconds() - 1));
    }

    @Override
    protected void waitUntilReady() {
        Unreliables.retryUntilTrue((int) startupTimeout.getSeconds(), TimeUnit.SECONDS, this::isConnected);
    }

    boolean isConnected() {
        try (Connection connection = DriverManager.getConnection(this.url, dbProps)) {
            logger.info(connection.getClientInfo().toString());
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public PostgresContainerWaitStrategy withTimes(int times) {
        this.times = times;
        return this;
    }
}
