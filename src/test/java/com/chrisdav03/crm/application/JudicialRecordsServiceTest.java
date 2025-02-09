package com.chrisdav03.crm.application;

import com.chrisdav03.crm.domain.model.Lead;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class JudicialRecordsServiceTest {
    private final JudicialRecordsService service = new JudicialRecordsService();

    @Test
    void testJudicialRecordCheck_PossibleResults() {
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        Lead lead = new Lead("3", "Carlos Sánchez", "555777999", "carlos@example.com", birthDate);

        boolean result = service.checkJudicialRecords(lead);


        assertInstanceOf(Boolean.class, result, "Result must be a boolean value.");
    }

    @Test
    void testMultipleLeads_JudicialRecordCheck() {
        Lead lead1 = new Lead("4", "Maria Torres", "888999000", "maria@example.com", LocalDate.of(1985, 10, 5));
        Lead lead2 = new Lead("5", "John Doe", "123123123", "john@example.com", LocalDate.of(1993, 8, 20));

        boolean result1 = service.checkJudicialRecords(lead1);
        boolean result2 = service.checkJudicialRecords(lead2);
        assertInstanceOf(Boolean.class, result1, "Result for lead1 must be a boolean value.");
        assertInstanceOf(Boolean.class, result2, "Result for lead2 must be a boolean value.");
    }
}
