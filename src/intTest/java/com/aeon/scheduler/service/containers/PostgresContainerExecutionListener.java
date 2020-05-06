package com.aeon.scheduler.service.containers;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresContainerExecutionListener extends AbstractTestExecutionListener {
    private final String CONTAINER_NAME = "postgres:9.6";
    private static final Logger logger = LoggerFactory.getLogger(PostgresContainerExecutionListener.class);
    private GenericContainer<?> pgContainer;

    @Override
    public void beforeTestExecution(TestContext testContext) throws Exception {
//        Environment env = testContext.getApplicationContext().getEnvironment();
//        final int port = Objects.requireNonNull(env.getProperty("postgres.db.port", Integer.class));
//        final String user = Objects.requireNonNull(env.getProperty("postgres.db.user"));
//        final String password = Objects.requireNonNull(env.getProperty("postgres.db.password"));
//        final String dbname = Objects.requireNonNull(env.getProperty("postgres.db.name"));
        final int port = 5432;
        final int hostPort = 5433;
        final String user = "postgres";
        final String password = "password";
        final String dbname = "scheduler_service";
        final String jdbcUrl = "jdbc:postgresql://localhost:" + hostPort + "/" + dbname;

        pgContainer = new PostgreSQLContainer<>(CONTAINER_NAME)
                .withExposedPorts(port)
                .withPassword(password)
                .withUsername(user)
                .withDatabaseName(dbname);
        pgContainer.setPortBindings(Collections.singletonList(hostPort + ":" + port));
        pgContainer.setWaitStrategy(new PostgresContainerWaitStrategy(user, password, jdbcUrl).withStartupTimeout(Duration.of(10, ChronoUnit.SECONDS)));
        pgContainer.start();
        logger.info("postgres container ready");
        super.beforeTestExecution(testContext);
    }

    @Override
    public void afterTestExecution(TestContext testContext) throws Exception {
        super.afterTestExecution(testContext);
        pgContainer.stop();
        logger.info("postgres container stopped");
    }
}
