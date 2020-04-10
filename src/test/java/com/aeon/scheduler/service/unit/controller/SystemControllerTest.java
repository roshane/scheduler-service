package com.aeon.scheduler.service.unit.controller;

import com.aeon.scheduler.service.controller.SystemController;
import com.aeon.scheduler.service.http.response.HealthResponse;
import com.aeon.scheduler.service.service.HealthService;
import com.aeon.scheduler.service.unit.AbstractBaseUnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Tag("controller")
class SystemControllerTest extends AbstractBaseUnitTest {
    private final HealthService mockHealthService = mock(HealthService.class);

    private SystemController createClassUnderTest() {
        return new SystemController(mockHealthService);
    }

    @Test
    @DisplayName("/health should return 200 Ok")
    void test_system_controller_returns_200ok() throws Exception {
        String fakeTimestamp = "2020-04-04T21:18:14";
        HealthResponse fakeResponse = new HealthResponse(fakeTimestamp, "1.0.0", "healthy", activeProfile);
        when(mockHealthService.getHealth()).thenReturn(fakeResponse);
        assertEquals(fakeResponse.toResponseString(), mockHealthService.getHealth().toResponseString());
        verify(mockHealthService).getHealth();
    }
}