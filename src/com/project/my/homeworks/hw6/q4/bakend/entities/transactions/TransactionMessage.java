package com.project.my.homeworks.hw6.q4.bakend.entities.transactions;

public enum TransactionMessage {
    INVALID_SOURCE("source account is NOT valid"),
    INVALID_AMOUNT("amount is NOT valid"),
    INVALID_BALANCE("account balance is NOT enough"),
    INVALID_DESTINATION("destination account is NOT valid"),
    INVALID_TRANSACTION("system failed while doing transaction"),
    VALID_TRANSACTION("transaction is done successfully.");

    private String description;

    TransactionMessage(String description) {
        this.description = description;
    }

    private String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
