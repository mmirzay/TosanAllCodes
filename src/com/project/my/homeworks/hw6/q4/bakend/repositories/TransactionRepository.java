package com.project.my.homeworks.hw6.q4.bakend.repositories;

import com.project.my.homeworks.hw6.q4.bakend.entities.transactions.Transaction;

import java.util.*;
import java.util.stream.Collectors;

public class TransactionRepository {
    Set<Transaction> transactions;

    public TransactionRepository() {
        transactions = new TreeSet<>();
    }

    public void insertTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> fetchAllTransactions() {
        return new ArrayList<>(transactions);
    }

    public List<Transaction> fetchAllTransactionsOfAccount(Long accountNumber) {
        return transactions.stream()
                .filter(t -> accountNumber.equals(t.getSourceAccountNumber()) || accountNumber.equals(t.getDestinationAccountNumber()))
                .collect(Collectors.toList());
    }
}
