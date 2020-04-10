package com.aeon.scheduler.service.unit;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.TimeUnit;

@Tag("UnitTest")
@Timeout(value = 1, unit = TimeUnit.SECONDS)
public abstract class AbstractBaseUnitTest {
    @Value("${spring.profiles.active}")
    protected String activeProfile;
}
