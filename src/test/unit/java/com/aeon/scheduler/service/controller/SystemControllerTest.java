package com.aeon.scheduler.service.controller;

import com.aeon.scheduler.service.response.HealthResponse;
import com.aeon.scheduler.service.service.HealthService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SystemControllerTest {
    private final HealthService mockHealthService = mock(HealthService.class);

    private SystemController createClassUnderTest() {
        return new SystemController(mockHealthService);
    }

    @Test
    void test_system_controller_returns_200ok() throws Exception {
        HealthResponse fakeResponse = new HealthResponse(LocalDateTime.of(LocalDate.of(2019, 1, 1), LocalTime.of(1, 1, 1)), "1.0.0", "healthy");
        when(mockHealthService.getHealth()).thenReturn(fakeResponse);
        assertEquals(fakeResponse.toResponseString(), mockHealthService.getHealth().toResponseString());
        verify(mockHealthService).getHealth();
    }
}