package com.project.my.homeworks.hw6.q4.bakend.entities.transactions;

public class InternalTransaction extends Transaction{
    public InternalTransaction(long srcAccountNumber, long destAccountNumber, double amount) {
        super(srcAccountNumber, destAccountNumber, amount);
    }
}
