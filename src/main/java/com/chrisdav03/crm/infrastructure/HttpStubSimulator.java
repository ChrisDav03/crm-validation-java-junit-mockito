package com.chrisdav03.crm.infrastructure;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HttpStubSimulator {
    public static boolean simulateExternalCall(int successRate) {
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(2) + 1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return new Random().nextInt(100) < successRate;
    }
}