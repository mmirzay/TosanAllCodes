package com.project.my.homeworks.hw6.q4.bakend.entities.transactions;

import com.project.my.homeworks.hw6.q4.bakend.entities.accounts.CreditCard;

public class CardToCardTransaction extends MobileBankTransaction {

    private String sourceCardNumber;
    private String destinationCardNumber;

    public CardToCardTransaction(CreditCard source, CreditCard destination, double amount) {
        super(source.getAccountNumber(), destination.getAccountNumber(), amount);
        sourceCardNumber = source.getNumber();
        destinationCardNumber = destination.getNumber();
    }

    @Override
    protected TransactionType getType() {
        return TransactionType.CARD_TO_CARD;
    }

    @Override
    public String toString() {
        return super.toString().formatted(getType().toString() + " from " + sourceCardNumber + " to " + destinationCardNumber);
    }

}
