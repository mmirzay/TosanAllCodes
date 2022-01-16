package com.project.my.homeworks.hw6.q4.bakend.entities.accounts;

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

    public void setExpired(){
        status = CreditCardStatus.EXPIRED;
    }

}
