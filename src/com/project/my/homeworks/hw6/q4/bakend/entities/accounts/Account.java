package com.project.my.homeworks.hw6.q4.bakend.entities.accounts;

import java.util.Objects;

public class Account {
    private long number;
    private double balance;
    private Iban iban;
    private AccountStatus status;
    private long customerId;
    private int bankCode;

    public Account(long number, long customerId, int bankCode) {
        this.number = number;
        this.customerId = customerId;
        this.bankCode = bankCode;
        this.status = AccountStatus.OPENED;
    }

    public long getNumber() {
        return number;
    }

    public long getCustomerId() {
        return customerId;
    }

    public double getBalance() {
        return balance;
    }

    public void increaseBalance(double balance) {
        this.balance += balance;
    }

    public void decreaseBalance(double balance) {
        this.balance -= balance;
    }

    public String getIban() {
        if (iban == null)
            iban = Iban.of(this.getNumber(), this.bankCode);
        return iban.getValue();
    }

    public int getBankCode() {
        return bankCode;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isOpened() {
        return status == AccountStatus.OPENED;
    }

    public void setOpened() {
        status = AccountStatus.OPENED;
    }

    public void setClosed() {
        status = AccountStatus.CLOSED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return number == account.number && customerId == account.customerId && bankCode == account.bankCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, customerId, bankCode);
    }

    @Override
    public String toString() {
        return "Account{" +
                "number=" + number +
                ", balance=" + balance +
                ", status=" + status +
                ", customerId=" + customerId +
                ", bankCode=" + bankCode +
                "}";
    }
}
