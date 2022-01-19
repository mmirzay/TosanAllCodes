package com.project.my.homeworks.hw6.q4.bakend.services;

import com.project.my.homeworks.hw6.q4.bakend.api.BankManagerInterface;
import com.project.my.homeworks.hw6.q4.bakend.entities.accounts.Account;
import com.project.my.homeworks.hw6.q4.bakend.entities.accounts.CreditCard;
import com.project.my.homeworks.hw6.q4.bakend.entities.banks.Bank;
import com.project.my.homeworks.hw6.q4.bakend.entities.transactions.*;
import com.project.my.homeworks.hw6.q4.bakend.entities.users.Customer;
import com.project.my.homeworks.hw6.q4.bakend.exceptions.*;
import com.project.my.homeworks.hw6.q4.bakend.repositories.*;
import com.project.my.homeworks.hw6.q4.utilities.BankRandomDataProducer;

import java.util.ArrayList;
import java.util.List;

public class BankManagerService implements BankManagerInterface {
    private final BankRepository bankRepository = new BankRepository();
    private final CustomerRepository customerRepository = new CustomerRepository();
    private final AccountRepository accountRepository = new AccountRepository();
    private final CreditCardRepository creditCardRepository = new CreditCardRepository();
    private final TransactionRepository transactionRepository = new TransactionRepository();

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

    @Override
    public void addCreditCard(CreditCard creditCard) {
        creditCardRepository.insertCreditCard(creditCard);
    }

    @Override
    public List<CreditCard> getAllCreditCards() {
        return creditCardRepository.fetchAllCreditCards();
    }

    @Override
    public List<CreditCard> getAllCreditCardsOfCustomer(long nationalId) {
        List<CreditCard> result = new ArrayList<>();
        for (Account account : accountRepository.fetchAllAccountsOfCustomer(nationalId)) {
            try {
                result.add(creditCardRepository.fetchCreditCardOfAccountNumber(account.getNumber()));
            } catch (CreditCardException ignored) {
            }
        }
        return result;
    }

    @Override
    public List<CreditCard> getAllCreditCardsOfBank(int bankCode) {
        List<CreditCard> result = new ArrayList<>();
        for (Account account : accountRepository.fetchAllAccountsOfBank(bankCode))
            try {
                result.add(creditCardRepository.fetchCreditCardOfAccountNumber(account.getNumber()));
            } catch (CreditCardException ignored) {
            }

        return result;
    }

    @Override
    public List<CreditCard> getAllCreditCardsOfCustomerInBank(long nationalId, int bankCode) {
        List<CreditCard> result = new ArrayList<>();
        for (Account account : accountRepository.fetchAccountOfCustomerInBank(nationalId, bankCode))
            try {
                result.add(creditCardRepository.fetchCreditCardOfAccountNumber(account.getNumber()));
            } catch (CreditCardException ignored) {
            }

        return result;
    }

    @Override
    public CreditCard getCreditCardByAccountNumber(long accountNumber) throws ServiceException {
        try {
            return creditCardRepository.fetchCreditCardOfAccountNumber(accountNumber);
        } catch (CreditCardException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public CreditCard getCreditCardByCardNumber(String cardNumber) throws ServiceException {
        try {
            return creditCardRepository.fetchCreditCardByNumber(cardNumber);
        } catch (CreditCardException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public CreditCard getCustomerCreditCardByCardNumber(long nationalId, String cardNumber) throws ServiceException {
        try {
            return getAllCreditCardsOfCustomer(nationalId).stream()
                    .filter(c -> c.getNumber().equals(cardNumber)).findFirst()
                    .orElseThrow(() -> new CreditCardException("credit card number is not exist"));
        } catch (CreditCardException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean reactivateCreditCard(String cardNumber) {
        try {
            CreditCard creditCard = creditCardRepository.fetchCreditCardByNumber(cardNumber);
            creditCard.setActive();
            creditCardRepository.updateCreditCard(creditCard);
            return true;
        } catch (CreditCardException e) {
            return false;
        }
    }

    @Override
    public String addDepositTransaction(long nationalId, int bankCode, DepositTransaction transaction) {
        Account sourceAccount = getAccountOfCustomerInBankByAccountNumber(nationalId, bankCode, transaction.getSourceAccountNumber());
        TransactionMessage message = TransactionMessage.VALID_TRANSACTION;
        if (sourceAccount == null || sourceAccount.isOpened() == false) {
            message = TransactionMessage.INVALID_SOURCE;
            return message.toString();
        } else if (transaction.getAmount() <= 0) {
            message = TransactionMessage.INVALID_AMOUNT;
            return message.toString();
        } else {
            sourceAccount.increaseBalance(transaction.getAmount());
            if (accountRepository.updateAccount(sourceAccount) == false) {
                message = TransactionMessage.INVALID_TRANSACTION;
                sourceAccount.decreaseBalance(transaction.getAmount());
            }
        }
        transaction.setMessage(message);
        return insertNewTransaction(transaction);
    }

    @Override
    public String addWithdrawTransaction(long nationalId, int bankCode, WithdrawTransaction transaction) {
        Account sourceAccount = getAccountOfCustomerInBankByAccountNumber(nationalId, bankCode, transaction.getSourceAccountNumber());
        TransactionMessage message = TransactionMessage.VALID_TRANSACTION;
        if (sourceAccount == null || sourceAccount.isOpened() == false) {
            message = TransactionMessage.INVALID_SOURCE;
            return message.toString();
        } else if (transaction.getAmount() <= 0) {
            message = TransactionMessage.INVALID_AMOUNT;
            return message.toString();
        } else if (sourceAccount.getBalance() < transaction.getAmount())
            message = TransactionMessage.INVALID_BALANCE;
        else {
            sourceAccount.decreaseBalance(transaction.getAmount());
            if (accountRepository.updateAccount(sourceAccount) == false) {
                message = TransactionMessage.INVALID_TRANSACTION;
                sourceAccount.increaseBalance(transaction.getAmount());
            }
        }
        transaction.setMessage(message);
        return insertNewTransaction(transaction);
    }

    @Override
    public String addInternalTransaction(long nationalId, int bankCode, InternalTransaction transaction) {
        Account sourceAccount = getAccountOfCustomerInBankByAccountNumber(nationalId, bankCode, transaction.getSourceAccountNumber());
        Account destinaionAccount = getAccountInBankByAccountNumber(bankCode, transaction.getDestinationAccountNumber());
        TransactionMessage message = TransactionMessage.VALID_TRANSACTION;
        if (sourceAccount == null || sourceAccount.isOpened() == false) {
            message = TransactionMessage.INVALID_SOURCE;
            return message.toString();
        } else if (destinaionAccount == null || destinaionAccount.isOpened() == false) {
            message = TransactionMessage.INVALID_DESTINATION;
            return message.toString();
        } else if (transaction.getAmount() <= 0) {
            message = TransactionMessage.INVALID_AMOUNT;
            return message.toString();
        } else if (sourceAccount.getBalance() < transaction.getAmount())
            message = TransactionMessage.INVALID_BALANCE;
        else {
            sourceAccount.decreaseBalance(transaction.getAmount());
            if (accountRepository.updateAccount(sourceAccount) == false) {
                message = TransactionMessage.INVALID_TRANSACTION;
                sourceAccount.increaseBalance(transaction.getAmount());
            } else {
                destinaionAccount.increaseBalance(transaction.getAmount());
                if (accountRepository.updateAccount(destinaionAccount) == false) {
                    message = TransactionMessage.INVALID_TRANSACTION;
                    destinaionAccount.decreaseBalance(transaction.getAmount());
                }
            }
        }
        transaction.setMessage(message);
        return insertNewTransaction(transaction);
    }

    @Override
    public String addExternalTransaction(ExternalTransaction transaction) {
        Account sourceAccount = null;
        Account destinaionAccount = null;
        try {
            sourceAccount = accountRepository.fetchAccountByNumber(transaction.getSourceAccountNumber());
            destinaionAccount = accountRepository.fetchAccountByNumber(transaction.getDestinationAccountNumber());
        } catch (AccountException e) {
            sourceAccount = null;
            destinaionAccount = null;
        }

        TransactionMessage message = TransactionMessage.VALID_TRANSACTION;
        if (sourceAccount == null || sourceAccount.isOpened() == false) {
            message = TransactionMessage.INVALID_SOURCE;
            return message.toString();
        } else if (destinaionAccount == null || destinaionAccount.isOpened() == false) {
            message = TransactionMessage.INVALID_DESTINATION;
            return message.toString();
        } else if (transaction.getAmount() <= 0) {
            message = TransactionMessage.INVALID_AMOUNT;
            return message.toString();
        } else if (sourceAccount.getBalance() < transaction.getAmount())
            message = TransactionMessage.INVALID_BALANCE;
        else {
            sourceAccount.decreaseBalance(transaction.getAmount());
            if (accountRepository.updateAccount(sourceAccount) == false) {
                message = TransactionMessage.INVALID_TRANSACTION;
                sourceAccount.increaseBalance(transaction.getAmount());
            } else {
                destinaionAccount.increaseBalance(transaction.getAmount());
                if (accountRepository.updateAccount(destinaionAccount) == false) {
                    message = TransactionMessage.INVALID_TRANSACTION;
                    destinaionAccount.decreaseBalance(transaction.getAmount());
                }
            }
        }
        transaction.setMessage(message);
        return insertNewTransaction(transaction);
    }

    @Override
    public String addCardToCardTransaction(CardToCardTransaction transaction) {
        Account sourceAccount = null;
        Account destinaionAccount = null;
        try {
            sourceAccount = accountRepository.fetchAccountByNumber(transaction.getSourceAccountNumber());
            destinaionAccount = accountRepository.fetchAccountByNumber(transaction.getDestinationAccountNumber());
        } catch (AccountException e) {
            sourceAccount = null;
            destinaionAccount = null;
        }

        TransactionMessage message = TransactionMessage.VALID_TRANSACTION;
        if (sourceAccount == null || sourceAccount.isOpened() == false) {
            message = TransactionMessage.INVALID_SOURCE;
            return message.toString();
        } else if (destinaionAccount == null || destinaionAccount.isOpened() == false) {
            message = TransactionMessage.INVALID_DESTINATION;
            return message.toString();
        } else if (transaction.getAmount() <= 0) {
            message = TransactionMessage.INVALID_AMOUNT;
            return message.toString();
        } else if (sourceAccount.getBalance() < transaction.getAmount())
            message = TransactionMessage.INVALID_BALANCE;
        else {
            sourceAccount.decreaseBalance(transaction.getAmount());
            if (accountRepository.updateAccount(sourceAccount) == false) {
                message = TransactionMessage.INVALID_TRANSACTION;
                sourceAccount.increaseBalance(transaction.getAmount());
            } else {
                destinaionAccount.increaseBalance(transaction.getAmount());
                if (accountRepository.updateAccount(destinaionAccount) == false) {
                    message = TransactionMessage.INVALID_TRANSACTION;
                    destinaionAccount.decreaseBalance(transaction.getAmount());
                }
            }
        }
        transaction.setMessage(message);
        return insertNewTransaction(transaction);
    }

    @Override
    public String addRechargingTransaction(RechargingTransaction transaction) {
        try {
            Account account = getAccountByNumber(transaction.getSourceAccountNumber());
            if (account.isOpened() == false)
                return TransactionMessage.INVALID_SOURCE.toString();
            if (transaction.getAmount() <= 0)
                return TransactionMessage.INVALID_AMOUNT.toString();
            TransactionMessage message = TransactionMessage.VALID_TRANSACTION;
            if (account.getBalance() < transaction.getAmount())
                message = TransactionMessage.INVALID_BALANCE;
            else {
                account.decreaseBalance(transaction.getAmount());
                if (accountRepository.updateAccount(account) == false) {
                    message = TransactionMessage.INVALID_TRANSACTION;
                    account.increaseBalance(transaction.getAmount());
                }
            }
            transaction.setMessage(message);
            insertNewTransaction(transaction);
            if (message == TransactionMessage.VALID_TRANSACTION)
                return BankRandomDataProducer.getRandomPhoneChargeCode();
            else
                return message.toString();
        } catch (ServiceException e) {
            return TransactionMessage.INVALID_SOURCE.toString();
        }
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.fetchAllTransactions();
    }

    @Override
    public List<Transaction> getAllTransactionsOfCustomer(long nationalId) {
        List<Transaction> result = new ArrayList<>();
        for (Account account : accountRepository.fetchAllAccountsOfCustomer(nationalId))
            result.addAll(transactionRepository.fetchAllTransactionsOfAccount(account.getNumber()));
        return result;
    }

    @Override
    public List<Transaction> getAllTransactionsOfCustomerInBank(long nationalId, int bankCode) {
        List<Transaction> result = new ArrayList<>();
        for (Account account : accountRepository.fetchAccountOfCustomerInBank(nationalId, bankCode))
            result.addAll(transactionRepository.fetchAllTransactionsOfAccount(account.getNumber()));
        return result;
    }

    @Override
    public List<Transaction> getAllTransactionsOfBank(int bankCode) {
        List<Transaction> result = new ArrayList<>();
        for (Account account : accountRepository.fetchAllAccountsOfBank(bankCode))
            result.addAll(transactionRepository.fetchAllTransactionsOfAccount(account.getNumber()));
        return result;
    }

    @Override
    public List<Transaction> getAllTransactionsOfAccount(long accountNumber) {
        return transactionRepository.fetchAllTransactionsOfAccount(accountNumber);
    }

    @Override
    public List<Transaction> getAllTransactionsOfCreditCard(String cardNumber) {
        try {
            CreditCard creditCard = creditCardRepository.fetchCreditCardByNumber(cardNumber);
            return transactionRepository.fetchAllTransactionsOfAccount(creditCard.getAccountNumber());
        } catch (CreditCardException e) {
            return null;
        }
    }

    private String insertNewTransaction(Transaction transaction) {
        transactionRepository.insertTransaction(transaction);
        return transaction.getMessage().toString();
    }
}
