package com.project.my.homeworks.hw6.q4.bakend.repositories;

import com.project.my.homeworks.hw6.q4.bakend.entities.accounts.Account;
import com.project.my.homeworks.hw6.q4.bakend.exceptions.AccountException;
import com.project.my.homeworks.hw6.q4.utilities.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountRepository {
    private Set<Account> accounts;

    public AccountRepository() {
        accounts = new HashSet<>();
    }

    public void insertAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> fetchAllAccounts() {
        return new ArrayList<>(accounts);
    }

    public Account fetchAccountByNumber(long accountNumber) throws AccountException {
        return accounts.stream()
                .filter(a -> a.getNumber() == accountNumber)
                .findFirst()
                .orElseThrow(() -> new AccountException("account number is not exist."));
    }

    public List<Account> fetchAllAccountsOfCustomer(long nationalId) {
        return accounts.stream()
                .filter(a -> a.getCustomerId() == nationalId)
                .collect(Collectors.toList());
    }

    public List<Account> fetchAllAccountsOfBank(int bankCode) {
        return accounts.stream()
                .filter(a -> a.getBankCode() == bankCode)
                .collect(Collectors.toList());
    }

    public List<Account> fetchAccountOfCustomerInBank(long nationalId, int bankCode) {
        return accounts.stream()
                .filter(a -> a.getBankCode() == bankCode && a.getCustomerId() == nationalId)
                .collect(Collectors.toList());
    }

    public boolean updateAccount(Account account) {
        try {
            Account toUpdate = fetchAccountByNumber(account.getNumber());
            toUpdate.setBalance(account.getBalance());
            return true;
        } catch (AccountException e) {
            Logger.printLogError(e.getMessage());
            return false;
        }
    }

    public boolean deleteAccountByNumber(long accountNumber) {
        try {
            Account toRemove = fetchAccountByNumber(accountNumber);
            return accounts.remove(toRemove);
        } catch (AccountException e) {
            Logger.printLogError(e.getMessage());
            return false;
        }
    }
}
