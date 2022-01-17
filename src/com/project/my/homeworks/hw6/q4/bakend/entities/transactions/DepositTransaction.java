package com.project.my.homeworks.hw6.q4.bakend.entities.transactions;

public class DepositTransaction extends Transaction {
    public DepositTransaction(long accountNumber, double amount) {
        super(accountNumber, null, amount);
    }

    @Override
    protected TransactionType getType() {
        return TransactionType.DEPOSIT;
    }

    @Override
    public String toString() {
        return super.toString().formatted(getType().toString()+ " to "+getSourceAccountNumber());
    }
}
