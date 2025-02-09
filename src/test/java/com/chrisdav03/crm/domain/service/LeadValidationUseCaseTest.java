package com.chrisdav03.crm.domain.service;

import com.chrisdav03.crm.application.NationalRegistryService;
import com.chrisdav03.crm.application.JudicialRecordsService;
import com.chrisdav03.crm.application.ProspectScoringService;
import com.chrisdav03.crm.domain.model.Lead;
import com.chrisdav03.crm.domain.model.Prospect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LeadValidationUseCaseTest {
    private LeadValidationUseCase validationUseCase;
    private NationalRegistryService nationalRegistryService;
    private JudicialRecordsService judicialRecordsService;
    private ProspectScoringService prospectScoringService;

    @BeforeEach
    void setUp() {
        nationalRegistryService = mock(NationalRegistryService.class);
        judicialRecordsService = mock(JudicialRecordsService.class);
        prospectScoringService = mock(ProspectScoringService.class);

        validationUseCase = new LeadValidationUseCase(
                nationalRegistryService, judicialRecordsService, prospectScoringService);
    }

    @Test
    void testLeadSuccessfullyConvertedToProspect() {
        LocalDate birthDate = LocalDate.of(1995, 5, 10);
        Lead lead = new Lead("1", "Juan Pérez", "123456789", "juan@example.com", birthDate);

        when(nationalRegistryService.validateLead(lead)).thenReturn(true);
        when(judicialRecordsService.checkJudicialRecords(lead)).thenReturn(true);
        when(prospectScoringService.getScore(lead)).thenReturn(75);

        Prospect prospect = validationUseCase.validateAndConvertLead(lead);

        assertNotNull(prospect, "Lead should be converted to a Prospect.");
        assertEquals("Juan Pérez", prospect.getName());
        assertEquals("123456789", prospect.getNationalId());
        assertEquals("juan@example.com", prospect.getEmail());
        assertEquals(birthDate, prospect.getBirthDate());
    }

    @Test
    void testLeadRejectedDueToNationalRegistryFailure() {
        LocalDate birthDate = LocalDate.of(1990, 3, 15);
        Lead lead = new Lead("2", "Ana López", "987654321", "ana@example.com", birthDate);

        when(nationalRegistryService.validateLead(lead)).thenReturn(false);
        when(judicialRecordsService.checkJudicialRecords(lead)).thenReturn(true);
        when(prospectScoringService.getScore(lead)).thenReturn(80);

        Prospect prospect = validationUseCase.validateAndConvertLead(lead);

        assertNull(prospect, "Lead should be rejected due to National Registry validation failure.");
    }

    @Test
    void testLeadRejectedDueToJudicialRecordFailure() {
        LocalDate birthDate = LocalDate.of(1987, 7, 25);
        Lead lead = new Lead("3", "Carlos Sánchez", "555777999", "carlos@example.com", birthDate);

        when(nationalRegistryService.validateLead(lead)).thenReturn(true);
        when(judicialRecordsService.checkJudicialRecords(lead)).thenReturn(false);
        when(prospectScoringService.getScore(lead)).thenReturn(90);

        Prospect prospect = validationUseCase.validateAndConvertLead(lead);

        assertNull(prospect, "Lead should be rejected due to Judicial Record validation failure.");
    }

    @Test
    void testLeadRejectedDueToLowScore() {
        LocalDate birthDate = LocalDate.of(1998, 11, 30);
        Lead lead = new Lead("4", "Laura Martínez", "222333444", "laura@example.com", birthDate);

        when(nationalRegistryService.validateLead(lead)).thenReturn(true);
        when(judicialRecordsService.checkJudicialRecords(lead)).thenReturn(true);
        when(prospectScoringService.getScore(lead)).thenReturn(50);

        Prospect prospect = validationUseCase.validateAndConvertLead(lead);

        assertNull(prospect, "Lead should be rejected due to low prospect score.");
    }
}
