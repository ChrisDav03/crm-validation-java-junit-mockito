package com.chrisdav03.crm.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class LeadTest {

    @Test
    void testLeadCreation() {
        LocalDate birthDate = LocalDate.of(1992, 3, 14);
        Lead lead = new Lead("1", "Juan Pérez", "123456789", "juan@example.com", birthDate);

        assertEquals("1", lead.getId());
        assertEquals("Juan Pérez", lead.getName());
        assertEquals("123456789", lead.getNationalId());
        assertEquals("juan@example.com", lead.getEmail());
        assertEquals(birthDate, lead.getBirthDate(), "Birthdate should match the expected value.");
    }

    @Test
    void testLeadToString() {
        LocalDate birthDate = LocalDate.of(1985, 7, 22);
        Lead lead = new Lead("2", "Maria Rodriguez", "987654321", "maria@example.com", birthDate);

        String result = lead.toString();

        assertTrue(result.contains("Maria Rodriguez"));
        assertTrue(result.contains("987654321"));
        assertTrue(result.contains("maria@example.com"));
        assertTrue(result.contains(birthDate.toString()), "Birthdate should be included in toString output.");
    }
}
