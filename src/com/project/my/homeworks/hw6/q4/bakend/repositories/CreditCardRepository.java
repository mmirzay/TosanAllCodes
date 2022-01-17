package com.project.my.homeworks.hw6.q4.bakend.repositories;

import com.project.my.homeworks.hw6.q4.bakend.entities.accounts.CreditCard;
import com.project.my.homeworks.hw6.q4.bakend.entities.users.Customer;
import com.project.my.homeworks.hw6.q4.bakend.exceptions.CreditCardException;
import com.project.my.homeworks.hw6.q4.bakend.exceptions.CustomerException;
import com.project.my.homeworks.hw6.q4.utilities.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CreditCardRepository {
    private Set<CreditCard> creditCards;

    public CreditCardRepository() {
        creditCards = new HashSet<>();
    }

    public void insertCreditCard(CreditCard creditCard) {
        creditCards.add(creditCard);
    }

    public List<CreditCard> fetchAllCreditCards() {
        return new ArrayList<>(creditCards);
    }

    public CreditCard fetchCreditCardByNumber(String cardNumber) throws CreditCardException {
        return creditCards.stream()
                .filter(c -> c.getNumber().equals(cardNumber))
                .findFirst().orElseThrow(() -> new CreditCardException("credit card number is not exist"));
    }

    public CreditCard fetchCreditCardOfAccountNumber(long accountNumber) throws CreditCardException {
        return creditCards.stream()
                .filter(c -> c.getAccountNumber()==accountNumber)
                .findFirst().orElseThrow(() -> new CreditCardException("credit card account number is not exist"));
    }

    public boolean updateCreditCard(CreditCard creditCard) {
        try {
            CreditCard toUpdate = fetchCreditCardByNumber(creditCard.getNumber());
            toUpdate.setFirstPassword(creditCard.getFirstPassword());
            toUpdate.setSecondPassword(creditCard.getSecondPassword());
            toUpdate.setStatus(creditCard.getStatus());
            return true;
        } catch (CreditCardException e) {
            Logger.printLogError(e.getMessage());
            return false;
        }
    }

    public boolean deleteCreditCardByNumber(String cardNumber) {
        try {
            CreditCard toRemove = fetchCreditCardByNumber(cardNumber);
            return creditCards.remove(toRemove);
        } catch (CreditCardException e) {
            Logger.printLogError(e.getMessage());
            return false;
        }
    }
}
