package com.project.my.homeworks.hw6.q4.bakend.entities.transactions;

public class WithdrawTransaction extends Transaction {

    public WithdrawTransaction(long srcAccountNumber, double amount) {
        super(srcAccountNumber, null, amount);
    }

}
