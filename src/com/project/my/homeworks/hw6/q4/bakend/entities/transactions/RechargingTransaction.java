package com.project.my.homeworks.hw6.q4.bakend.entities.transactions;

public class RechargingTransaction extends MobileBankTransaction {
    public RechargingTransaction(long accountNumber, double amount) {
        super(accountNumber, null, amount);
    }

    @Override
    protected TransactionType getType() {
        return TransactionType.RECHARGING_CODE;
    }
}
