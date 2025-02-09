package com.chrisdav03.crm.application;

import com.chrisdav03.crm.domain.model.Lead;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ProspectScoringServiceTest {
    private final ProspectScoringService prospectScoringService = new ProspectScoringService();

    @Test
    void testScoreWithinValidRange() {
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        Lead lead = new Lead("5", "Mario Gómez", "1010101010", "mario@example.com", birthDate);

        int score = prospectScoringService.getScore(lead);

        assertTrue(score >= 0 && score <= 100, "Score must be between 0 and 100.");
    }

    @Test
    void testMultipleScores_Variability() {
        Lead lead = new Lead("6", "Anna Johnson", "2020202020", "anna@example.com", LocalDate.of(1995, 2, 10));

        Set<Integer> scoreSet = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            scoreSet.add(prospectScoringService.getScore(lead));
        }

        assertTrue(scoreSet.size() > 1, "Random score should vary across multiple calls.");
    }
}
