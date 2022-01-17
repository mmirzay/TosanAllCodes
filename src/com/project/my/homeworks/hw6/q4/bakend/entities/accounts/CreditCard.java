package com.project.my.homeworks.hw6.q4.bakend.entities.accounts;

import java.util.Objects;

public class CreditCard {

    private String number;
    private String firstPassword;
    private String secondPassword;
    private ExpireDate expireDate;
    private int cvv;
    private CreditCardStatus status;
    private long accountNumber;

    public CreditCard(String number, ExpireDate expireDate, int cvv, String firstPassword, long accountNumber) {
        this.accountNumber = accountNumber;
        this.number = number;
        this.expireDate = expireDate;
        this.cvv = cvv;
        this.firstPassword = firstPassword;
        this.status = CreditCardStatus.ACTIVE;
    }

    public String getNumber() {
        return number;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getFirstPassword() {
        return firstPassword;
    }

    public String getSecondPassword() {
        return secondPassword;
    }

    public void setSecondPassword(String secondPassword) {
        this.secondPassword = secondPassword;
    }

    public ExpireDate getExpireDate() {
        return expireDate;
    }

    public int getCvv() {
        return cvv;
    }

    public CreditCardStatus getStatus() {
        return status;
    }

    public boolean isActive() {
        return status == CreditCardStatus.ACTIVE;
    }

    public void setActive() {
        status = CreditCardStatus.ACTIVE;
    }

    public void setInactive(){
        status = CreditCardStatus.INACTIVE;
    }

    public void setFirstPassword(String firstPassword) {
        this.firstPassword = firstPassword;
    }

    public void setStatus(CreditCardStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return accountNumber == that.accountNumber && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, accountNumber);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "number='" + number + '\'' +
                ", expireDate=" + expireDate +
                ", cvv=" + cvv +
                ", status=" + status +
                ", accountNumber=" + accountNumber +
                '}';
    }
}
