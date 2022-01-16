package com.project.my.homeworks.hw6.q4.bakend.entities.accounts;

public final class Iban {
    private final String CC = "IR";

    private long accountNumber;
    private int bankCode;
    private String value;
    private String bban;
    private String cd;
    private IbanType type;

    private Iban(long accountNumber, int bankCode) {
        this.accountNumber = accountNumber;
        this.bankCode = bankCode;
        calculateIban();
    }

    private void calculateIban() {
        String strBankCode = bankCode < 100 ? "0" + bankCode : "" + bankCode;
        String strAccountNumber = String.valueOf(accountNumber);
        strAccountNumber = "0".repeat(18 - strAccountNumber.length()) + strAccountNumber;
        cd = (int) (98 - accountNumber % 97) + "";
        type = IbanType.DEPOSIT;
        String strType = type.getValue() + "";
        bban = strBankCode + strType + strAccountNumber;
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
