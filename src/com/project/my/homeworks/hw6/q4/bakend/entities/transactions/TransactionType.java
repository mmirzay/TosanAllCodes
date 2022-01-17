package com.project.my.homeworks.hw6.q4.bakend.entities.transactions;

public enum TransactionType {
    DEPOSIT("Deposit"),
    WITHDRAW("Withdraw"),
    CARD_TO_CARD("Cart to Card"),
    EXTERNAL("External"),
    INTERNAL("Internal"),
    RECHARGING_CODE("Recharge Code");

    private String value;

    TransactionType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
