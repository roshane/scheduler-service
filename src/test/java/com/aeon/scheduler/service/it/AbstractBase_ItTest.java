package com.aeon.scheduler.service.it;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Timeout;
import org.springframework.boot.test.context.SpringBootTest;

@Tag("IntegrationTest")
@Timeout(5)
@SpringBootTest
public abstract class AbstractBase_ItTest {
}
