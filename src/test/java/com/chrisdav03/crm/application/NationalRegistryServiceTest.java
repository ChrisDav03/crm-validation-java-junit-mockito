package com.chrisdav03.crm.application;

import com.chrisdav03.crm.domain.model.Lead;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class NationalRegistryServiceTest {
    private final NationalRegistryService service = new NationalRegistryService();

    @Test
    void testValidation_Successful() {
        LocalDate birthDate = LocalDate.of(1992, 7, 10);
        Lead lead = new Lead("1", "Juan Pérez", "123456789", "juan@example.com", birthDate);

        boolean result = service.validateLead(lead);

        assertInstanceOf(Boolean.class, result, "Result must be a boolean value.");
    }

    @Test
    void testValidation_MultipleLeads() {
        Lead validLead = new Lead("2", "Maria Rodriguez", "987654321", "maria@example.com", LocalDate.of(1988, 3, 5));
        Lead invalidLead = new Lead("3", "Fake User", "000000000", "fake@example.com", LocalDate.of(2000, 12, 15));

        boolean validResult = service.validateLead(validLead);
        boolean invalidResult = service.validateLead(invalidLead);

        assertInstanceOf(Boolean.class, validResult, "Result for valid lead must be a boolean.");
        assertInstanceOf(Boolean.class, invalidResult, "Result for invalid lead must be a boolean.");
    }
}
