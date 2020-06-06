package com.aeon.scheduler.service;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Timeout;

@Tag("UnitTest")
@Timeout(value = 1)
public abstract class AbstractBaseUnitTest {
    protected final String fakeActiveProfile = "dev";
}
