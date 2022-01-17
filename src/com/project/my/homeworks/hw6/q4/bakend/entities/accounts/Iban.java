package com.project.my.homeworks.hw6.q4.bakend.entities.accounts;

public final class Iban {
    private final static String CC = "IR";

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
        cd = "0".repeat(2 - cd.length()) + cd;
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

    @Override
    public String toString() {
        return getValue();
    }

    public static long convertToAccountNumber(String iban) {
        if (iban.length() != 26)
            return 0;
        if (iban.startsWith(CC) == false)
            return 0;
        String strAccountNumber = iban.substring(8);

        try {
            return Long.parseLong(strAccountNumber);
        } catch (Exception e) {
            return 0;
        }
    }
}
