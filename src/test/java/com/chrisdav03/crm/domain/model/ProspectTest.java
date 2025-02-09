package com.chrisdav03.crm.domain.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ProspectTest {

    @Test
    void testCreateProspect() {
        LocalDate birthDate = LocalDate.of(1995, 4, 22);
        Prospect prospect = new Prospect("1", "Ana López", "987654321", "ana@example.com", birthDate);

        assertEquals("1", prospect.getId());
        assertEquals("Ana López", prospect.getName());
        assertEquals("987654321", prospect.getNationalId());
        assertEquals("ana@example.com", prospect.getEmail());
        assertEquals(birthDate, prospect.getBirthDate());
    }

    @Test
    void testToString() {
        LocalDate birthDate = LocalDate.of(1988, 7, 15);
        Prospect prospect = new Prospect("2", "Carlos Gómez", "555777999", "carlos@example.com", birthDate);
        String result = prospect.toString();

        assertTrue(result.contains("Carlos Gómez"));
        assertTrue(result.contains("555777999"));
        assertTrue(result.contains("carlos@example.com"));
        assertTrue(result.contains(birthDate.toString()));
    }
}
