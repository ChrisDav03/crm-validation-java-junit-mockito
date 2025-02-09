package com.chrisdav03.crm.application;


import com.chrisdav03.crm.domain.model.Lead;
import java.util.Random;

public class ProspectScoringService {
    public int getScore(Lead lead) {
        return new Random().nextInt(101);
    }
}
