package com.aeon.scheduler.service.it.controller;

import com.aeon.scheduler.service.it.AbstractBase_ItTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class SystemController_ItTest extends AbstractBase_ItTest {

    @Test
    void test_health_should_return_200ok(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/health")).andExpect(status().isOk())
                .andExpect(content().string(containsString("healthy")));
    }
}