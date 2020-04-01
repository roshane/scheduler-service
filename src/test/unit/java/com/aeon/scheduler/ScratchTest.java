package com.aeon.scheduler;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ScratchTest {
    @Test
    public void test_simple_assert() {
        String answer = "TRUE";
        assertThat(Boolean.valueOf(answer), is(true));
    }
}
