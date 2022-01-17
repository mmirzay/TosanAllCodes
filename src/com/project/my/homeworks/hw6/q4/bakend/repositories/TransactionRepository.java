package com.project.my.homeworks.hw6.q4.bakend.repositories;

import com.project.my.homeworks.hw6.q4.bakend.entities.transactions.Transaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TransactionRepository {
    Set<Transaction> transactions;

    public TransactionRepository() {
        transactions = new HashSet<>();
    }

    public void insertTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> fetchAllTransactions() {
        return new ArrayList<>(transactions);
    }

    public List<Transaction> fetchAllTransactionsOfAccount(long accountNumber) {
        return transactions.stream()
                .filter(t -> t.getSourceAccountNumber() == accountNumber)
                .collect(Collectors.toList());
    }
}
