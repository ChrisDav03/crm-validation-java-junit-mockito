package com.chrisdav03.crm.domain.service;

import com.chrisdav03.crm.domain.model.Lead;
import com.chrisdav03.crm.domain.model.Prospect;
import com.chrisdav03.crm.application.NationalRegistryService;
import com.chrisdav03.crm.application.JudicialRecordsService;
import com.chrisdav03.crm.application.ProspectScoringService;

import java.util.concurrent.CompletableFuture;

public class LeadValidationUseCase {
    private final NationalRegistryService nationalRegistryService;
    private final JudicialRecordsService judicialRecordsService;
    private final ProspectScoringService prospectScoringService;

    public LeadValidationUseCase(NationalRegistryService nationalRegistryService,
                                 JudicialRecordsService judicialRecordsService,
                                 ProspectScoringService prospectScoringService) {
        this.nationalRegistryService = nationalRegistryService;
        this.judicialRecordsService = judicialRecordsService;
        this.prospectScoringService = prospectScoringService;
    }

    public Prospect validateAndConvertLead(Lead lead) {
        System.out.println("\n Starting validation for Lead: " + lead.getName());

        CompletableFuture<Boolean> registryCheck = CompletableFuture.supplyAsync(() ->
                nationalRegistryService.validateLead(lead));

        CompletableFuture<Boolean> judicialCheck = CompletableFuture.supplyAsync(() ->
                judicialRecordsService.checkJudicialRecords(lead));

        CompletableFuture<Void> allChecks = CompletableFuture.allOf(registryCheck, judicialCheck);

        allChecks.join();

        boolean registryValid = registryCheck.join();
        boolean judicialValid = judicialCheck.join();

        if (!registryValid) {
            System.out.println("Lead validation failed: Not found in National Registry.");
            return null;
        }

        if (!judicialValid) {
            System.out.println("Lead validation failed: Judicial records found.");
            return null;
        }

        int score = prospectScoringService.getScore(lead);
        System.out.println("Score obtained: " + score);

        if (score > 60) {
            System.out.println("Lead approved and converted to Prospect.");
            return new Prospect(lead.getId(), lead.getName(), lead.getNationalId(), lead.getEmail(), lead.getBirthDate());
        }

        System.out.println("Lead rejected: Score validation failed.");
        return null;
    }
}
