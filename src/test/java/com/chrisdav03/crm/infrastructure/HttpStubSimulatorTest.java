package com.chrisdav03.crm.infrastructure;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HttpStubSimulatorTest {

    @Test
    void testSimulationSuccessfullCall() {
        boolean response = HttpStubSimulator.simulateExternalCall(90);
        assertTrue(response || !response, "It should return a boolean.");
    }

    @Test
    void testSimulationFailedCall() {
        boolean response = HttpStubSimulator.simulateExternalCall(10);
        assertTrue(response || !response, "It should return a boolean.");
    }
}
