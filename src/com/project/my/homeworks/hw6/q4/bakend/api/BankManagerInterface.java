package com.project.my.homeworks.hw6.q4.bakend.api;

import com.project.my.homeworks.hw6.q4.bakend.entities.accounts.Account;
import com.project.my.homeworks.hw6.q4.bakend.entities.accounts.CreditCard;
import com.project.my.homeworks.hw6.q4.bakend.entities.banks.Bank;
import com.project.my.homeworks.hw6.q4.bakend.entities.transactions.*;
import com.project.my.homeworks.hw6.q4.bakend.entities.users.Customer;
import com.project.my.homeworks.hw6.q4.bakend.exceptions.ServiceException;

import java.util.List;

public interface BankManagerInterface {


    List<Bank> getAllBanksList();

    Bank getBankByCode(int bankCode) throws ServiceException;

    void addCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomerById(long nationalId) throws ServiceException;

    void addAccount(Account account);

    List<Account> getAllAccounts();

    List<Account> getAllAccountsOfCustomer(long nationalId);

    List<Account> getAllAccountsOfBank(int bankCode);

    List<Account> getAllAccountsOfCustomerInBank(long nationalId, int bankCode);

    Account getAccountByNumber(long accountNumber) throws ServiceException;

    boolean closeAccount(long accountNumber);

    boolean reopenAccount(long accountNumber);

    void addCreditCard(CreditCard creditCard);

    List<CreditCard> getAllCreditCards();

    List<CreditCard> getAllCreditCardsOfCustomer(long nationalId);

    List<CreditCard> getAllCreditCardsOfBank(int bankCode);

    List<CreditCard> getAllCreditCardsOfCustomerInBank(long nationalId, int bankCode);

    CreditCard getCreditCardByAccountNumber(long accountNumber) throws ServiceException;

    CreditCard getCreditCardByCardNumber(String cardNumber) throws ServiceException;

    CreditCard getCustomerCreditCardByCardNumber(long nationalId, String sourceCardNumber) throws ServiceException;

    boolean reactivateCreditCard(String cardNumber);

}
