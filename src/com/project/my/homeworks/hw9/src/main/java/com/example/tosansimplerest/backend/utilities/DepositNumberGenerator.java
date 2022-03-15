package com.example.tosansimplerest.backend.utilities;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public final class DepositNumberGenerator {
    private static final Set<Long> accountNumbers = new HashSet<>();

    public static long getRandomAccountNumber() {
        long result = RandomGenerator.getRandomInt(11111, 99999999);
        while (accountNumbers.contains(result))
            result = RandomGenerator.getRandomInt(11111, 99999999);
        accountNumbers.add(result);
        return result;
    }

    private static class RandomGenerator {
        public static int getRandomInt(int base, int max) {
            return base + new Random().nextInt(max - base + 1);
        }
    }
}