package com.project.my.homeworks.hw6.q4.bakend.entities.transactions;

public class WithdrawTransaction extends Transaction {

    public WithdrawTransaction(long srcAccountNumber, double amount) {
        super(srcAccountNumber, null, amount);
    }

    @Override
    protected TransactionType getType() {
        return TransactionType.WITHDRAW;
    }

    @Override
    public String toString() {
        return super.toString().formatted(getType().toString() + " of " + getSourceAccountNumber());
    }
}
