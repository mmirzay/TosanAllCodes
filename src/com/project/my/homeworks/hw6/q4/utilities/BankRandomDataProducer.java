package com.project.my.homeworks.hw6.q4.utilities;

import com.project.my.homeworks.hw6.q4.bakend.entities.accounts.ExpireDate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class BankRandomDataProducer {
    private static final Set<Long> accountNumbers = new HashSet<>();
    private static final Map<Long, String> accountCards = new HashMap<>();

    public static long getRandomAccountNumber() {
        long result = RandomGenerator.getRandomInt(11111, 99999999);
        while (accountNumbers.contains(result))
            result = RandomGenerator.getRandomInt(11111, 99999999);
        accountNumbers.add(result);
        return result;
    }

    public static String getRandomCardNumberForAccount(long accountNumber, int bankCode) {
        if (accountCards.containsKey(accountNumber) == false) {
            String cardNumber = getRandomCardNumber(bankCode);
            while (accountCards.values().contains(cardNumber))
                cardNumber = getRandomCardNumber(bankCode);
            accountCards.put(accountNumber, cardNumber);
        }
        return accountCards.get(accountNumber);
    }

    private static String getRandomCardNumber(int bankCode) {
        StringBuilder result = new StringBuilder();
        int numberOfDigits = 16;
        String first4Digits = switch (bankCode) {
            case 18 -> "5859";
            case 12 -> "6104";
            case 15 -> "5892";
            case 19 -> "6037";
            default -> "0000";
        };
        result.append(first4Digits);
        for (int i = 1; i < numberOfDigits / 4; i++)
            result.append(RandomGenerator.getRandomInt(1111, 9999));
        return result.toString();
    }

    public static ExpireDate getRandomExpireDate() {
        int year = RandomGenerator.getRandomInt(1400, 1410);
        int month = RandomGenerator.getRandomInt(1, 12);
        return ExpireDate.of(year, month);
    }

    public static int getRandomCvv() {
        return RandomGenerator.getRandomInt(111, 9999);
    }

    public static String getRandomPassword() {
        StringBuilder result = new StringBuilder();
        result.append(RandomGenerator.getRandomInt(1111, 9999));
        return result.toString();
    }

    public static String getRandomPhoneChargeCode() {
        StringBuilder result = new StringBuilder();
        int numberOfDigits = 12;
        for (int i = 1; i <= numberOfDigits / 4; i++)
            result.append(RandomGenerator.getRandomInt(1111, 9999));
        return result.toString();
    }
}
