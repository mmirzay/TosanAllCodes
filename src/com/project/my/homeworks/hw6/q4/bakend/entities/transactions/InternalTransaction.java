package com.project.my.homeworks.hw6.q4.bakend.entities.transactions;

public class InternalTransaction extends Transaction {
    public InternalTransaction(long srcAccountNumber, long destAccountNumber, double amount) {
        super(srcAccountNumber, destAccountNumber, amount);
    }

    @Override
    protected TransactionType getType() {
        return TransactionType.INTERNAL;
    }

    @Override
    public String toString() {
        return super.toString().formatted(getType().toString() + " from" + getSourceAccountNumber() + "to " + getDestinationAccountNumber());
    }
}
