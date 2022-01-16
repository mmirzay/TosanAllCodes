package com.project.my.homeworks.hw6.q4.bakend.entities.transactions;

public class DepositTransaction extends Transaction {

    public DepositTransaction(long accountNumber, double amount) {
        super(accountNumber, null, amount);
    }
}
