package com.chrisdav03.crm.application;

import com.chrisdav03.crm.domain.model.Lead;
import com.chrisdav03.crm.infrastructure.HttpStubSimulator;

public class NationalRegistryService {
    public boolean validateLead(Lead lead) {
        return HttpStubSimulator.simulateExternalCall(50);
    }
}
