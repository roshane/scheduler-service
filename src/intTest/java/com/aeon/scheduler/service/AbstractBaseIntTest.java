package com.aeon.scheduler.service;

import com.aeon.scheduler.service.containers.PostgresContainerExecutionListener;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Timeout;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;

@Tag("IntegrationTest")
@Timeout(5)
@SpringBootTest
@TestExecutionListeners({PostgresContainerExecutionListener.class})
public abstract class AbstractBaseIntTest {
}

