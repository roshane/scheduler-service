package com.aeon.scheduler.service;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Value;

@Tag("UnitTest")
@Timeout(value = 1)
public abstract class AbstractBaseUnitTest {
    protected String fakeActiveProfile = "dev";
}
