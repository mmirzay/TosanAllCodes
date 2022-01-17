package com.project.my.homeworks.hw6.q4.bakend.entities.transactions;

public abstract class MobileBankTransaction extends Transaction {
    public MobileBankTransaction(long srcAccountNumber, Long destAccountNumber, double amount) {
        super(srcAccountNumber, destAccountNumber == null ? null : destAccountNumber, amount);
    }
}
