package com.project.my.homeworks.hw6.q4.bakend.services;

import com.project.my.homeworks.hw6.q4.bakend.api.BankManagerInterface;
import com.project.my.homeworks.hw6.q4.bakend.entities.accounts.Account;
import com.project.my.homeworks.hw6.q4.bakend.entities.banks.Bank;
import com.project.my.homeworks.hw6.q4.bakend.entities.users.Customer;
import com.project.my.homeworks.hw6.q4.bakend.exceptions.AccountException;
import com.project.my.homeworks.hw6.q4.bakend.exceptions.BankException;
import com.project.my.homeworks.hw6.q4.bakend.exceptions.CustomerException;
import com.project.my.homeworks.hw6.q4.bakend.exceptions.ServiceException;
import com.project.my.homeworks.hw6.q4.bakend.repositories.*;

import java.util.List;

public class BankManagerService implements BankManagerInterface {
    private final BankRepository bankRepository = new BankRepository();
    private final CustomerRepository customerRepository = new CustomerRepository();
    private final AccountRepository accountRepository = new AccountRepository();

    @Override
    public List<Bank> getAllBanksList() {
        return bankRepository.fetchAllBanks();
    }

    @Override
    public Bank getBankByCode(int bankCode) throws ServiceException {
        try {
            return bankRepository.fetchBankByCode(bankCode);
        } catch (BankException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.insertCustomer(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.fetchAllCustomers();
    }

    @Override
    public Customer getCustomerById(long nationalId) throws ServiceException {
        try {
            return customerRepository.fetchCustomerById(nationalId);
        } catch (CustomerException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addAccount(Account account) {
        accountRepository.insertAccount(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.fetchAllAccounts();
    }

    @Override
    public List<Account> getAllAccountsOfCustomer(long nationalId) {
        return accountRepository.fetchAllAccountsOfCustomer(nationalId);
    }

    @Override
    public List<Account> getAllAccountsOfBank(int bankCode) {
        return accountRepository.fetchAllAccountsOfBank(bankCode);
    }

    @Override
    public List<Account> getAllAccountsOfCustomerInBank(long nationalId, int bankCode) {
        return accountRepository.fetchAccountOfCustomerInBank(nationalId, bankCode);
    }

    @Override
    public Account getAccountByNumber(long accountNumber) throws ServiceException {
        try {
            return accountRepository.fetchAccountByNumber(accountNumber);
        } catch (AccountException e) {
            throw new ServiceException(e);
        }
    }

    private Account getAccountOfCustomerInBankByAccountNumber(long nationalId, int bankCode, long accountNumber) {
        return getAllAccountsOfCustomerInBank(nationalId, bankCode).stream().filter(a -> a.getNumber() == accountNumber).findFirst().orElse(null);
    }

    private Account getAccountInBankByAccountNumber(int bankCode, long accountNumber) {
        return getAllAccountsOfBank(bankCode).stream().filter(a -> a.getNumber() == accountNumber).findFirst().orElse(null);
    }

    @Override
    public boolean closeAccount(long accountNumber) {
        try {
            Account account = accountRepository.fetchAccountByNumber(accountNumber);
            account.setClosed();
            accountRepository.updateAccount(account);
            return true;
        } catch (AccountException e) {
            return false;
        }
    }

    @Override
    public boolean reopenAccount(long accountNumber) {
        try {
            Account account = accountRepository.fetchAccountByNumber(accountNumber);
            account.setOpened();
            accountRepository.updateAccount(account);
            return true;
        } catch (AccountException e) {
            return false;
        }
    }
}