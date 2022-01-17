package com.project.my.homeworks.hw6.q4.bakend.entities.transactions;

import com.project.my.homeworks.hw6.q4.bakend.entities.accounts.Iban;

public class ExternalTransaction extends Transaction {
    public ExternalTransaction(Iban srcIban, Iban destIban, double amount) {
        super(srcIban.getAccountNumber(), destIban.getAccountNumber(), amount);
    }

    @Override
    protected TransactionType getType() {
        return TransactionType.EXTERNAL;
    }

    @Override
    public String toString() {
        return super.toString().formatted(getType().toString() + " from" + getSourceAccountNumber() + "to " + getDestinationAccountNumber());
    }
}
