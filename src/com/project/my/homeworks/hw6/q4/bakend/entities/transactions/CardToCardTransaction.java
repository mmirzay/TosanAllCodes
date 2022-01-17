package com.project.my.homeworks.hw6.q4.bakend.entities.transactions;

import com.project.my.homeworks.hw6.q4.bakend.entities.accounts.CreditCard;

public class CardToCardTransaction extends MobileBankTransaction {

    public CardToCardTransaction(CreditCard source, CreditCard destination, double amount) {
        super(source.getAccountNumber(), destination.getAccountNumber(), amount);
    }

    @Override
    protected TransactionType getType() {
        return TransactionType.CARD_TO_CARD;
    }

}
