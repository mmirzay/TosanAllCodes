package com.project.my.homeworks.hw6.q4.bakend.entities.accounts;

public final class Iban {
    private final String CC = "IR";

    private long accountNumber;
    private int bankCode;
    private String value;

    private Iban(long accountNumber, int bankCode) {
        this.accountNumber = accountNumber;
        this.bankCode = bankCode;
        calculateIban();
    }

    private void calculateIban() {
        int type = 0;//deposit
        long bban = Long.parseLong(bankCode + "" + type + "" + accountNumber);
        int cd = (int) (98 - bban % 97);
        value = CC + cd + bban;
    }

    public String getValue() {
        return value;
    }

    public static Iban of(long accountNumber, int bankCode) {
        return new Iban(accountNumber, bankCode);
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public int getBankCode() {
        return bankCode;
    }
}
