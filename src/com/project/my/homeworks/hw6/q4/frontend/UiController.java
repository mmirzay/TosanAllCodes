package com.project.my.homeworks.hw6.q4.frontend;

import com.project.my.homeworks.hw6.q4.bakend.api.BankManagerInterface;
import com.project.my.homeworks.hw6.q4.bakend.entities.accounts.Account;
import com.project.my.homeworks.hw6.q4.bakend.entities.accounts.CreditCard;
import com.project.my.homeworks.hw6.q4.bakend.entities.accounts.ExpireDate;
import com.project.my.homeworks.hw6.q4.bakend.entities.accounts.Iban;
import com.project.my.homeworks.hw6.q4.bakend.entities.banks.Bank;
import com.project.my.homeworks.hw6.q4.bakend.entities.transactions.*;
import com.project.my.homeworks.hw6.q4.bakend.entities.users.Customer;
import com.project.my.homeworks.hw6.q4.bakend.exceptions.ServiceException;
import com.project.my.homeworks.hw6.q4.bakend.services.BankManagerService;
import com.project.my.homeworks.hw6.q4.utilities.BankRandomDataProducer;
import com.project.my.homeworks.hw6.q4.utilities.Validator;

import java.util.List;

public class UiController {
    private final BankManagerInterface bankManager = new BankManagerService();
    private final UiViewer ui = new UiViewer();
    private boolean isRunning;

    public void run() {
        try {
            ui.showLog("Running Bank Manager.");
            isRunning = true;
            while (isRunning)
                showMenu();
            ui.showLog("Bank manager is closed.");
        } catch (Exception e) {
            ui.showInfoMessage("error happen: " + e.getMessage() + " Please try again!");
            run();
        }
    }

    private void showMenu() {
        ui.showMainMenu();
        switch (ui.getIntInputValue()) {
            case 1 -> loginAsAdmin();
            case 2 -> loginAsCustomer();
            case 3 -> isRunning = false;
            default -> ui.showInvalidInputMessage();
        }
    }

    private void loginAsAdmin() {
        String password = ui.getOptionalStringInputValue("Enter password (admin):");
        if (password.equals("admin"))
            showAdminPage();
        else
            ui.showInvalidInputMessage();
    }

    private void showAdminPage() {
        ui.showAdminMenu();
        switch (ui.getIntInputValue()) {
            case 1:
                showBanksList();
                break;
            case 2:
                showAdminCustomersList();
                break;
            case 3:
                showAdminAccountsList();
                break;
            case 4:
                showAdminCreditCardsList();
                break;
            case 5:
                showAdminAccountsOfCustomer();
                break;
            case 6:
                showAdminAccountsOfBank();
                break;
            case 7:
                showAdminCreditCardsOfCustomer();
                break;
            case 8:
                showAdminCreditCardsOfBank();
                break;
            case 9:
                showAdminReactiveCreditCard();
                break;
            case 10:
                showAdminCloseAccount();
                break;
            case 11:
                showAdminReopenAccount();
                break;
            case 12:
                showAdminTransactionsList();
                break;
            case 13:
                showAdminTransactionsOfCustomer();
                break;
            case 14:
                showAdminTransactionsOfBank();
                break;
            case 15:
                showAdminTransactionsOfAccount();
                break;
            case 16:
                showAdminTransactionsOfCreditCard();
                break;
            case 17:
                return;
            default:
                ui.showInvalidInputMessage();
        }
        showAdminPage();
    }

    private void showBanksList() {
        List<Bank> banks = bankManager.getAllBanksList();
        ui.showListWithTitle("Banks List", banks);
    }

    private void showAdminCustomersList() {
        List<Customer> customers = bankManager.getAllCustomers();
        ui.showListWithTitle("Customers List", customers);
    }

    private void showAdminAccountsList() {
        List<Account> accounts = bankManager.getAllAccounts();
        ui.showListWithTitle("Accounts List", accounts);
    }

    private void showAdminCreditCardsList() {
        List<CreditCard> creditCards = bankManager.getAllCreditCards();
        ui.showListWithTitle("Credit Cards List", creditCards);
    }

    private void showAdminAccountsOfCustomer() {
        long nationalId = ui.getLongInputValue("Enter customer national id:");
        List<Account> accounts = bankManager.getAllAccountsOfCustomer(nationalId);
        ui.showListWithTitle("Accounts of Customer (" + nationalId + ")", accounts);
    }

    private void showAdminAccountsOfBank() {
        int bankCode = ui.getIntInputValue("Enter bank code:");
        List<Account> accounts = bankManager.getAllAccountsOfBank(bankCode);
        ui.showListWithTitle("Accounts of Bank (" + bankCode + ")", accounts);
    }

    private void showAdminCreditCardsOfCustomer() {
        long nationalId = ui.getLongInputValue("Enter customer national id:");
        List<CreditCard> creditCards = bankManager.getAllCreditCardsOfCustomer(nationalId);
        ui.showListWithTitle("Credit Cards of Customer (" + nationalId + ")", creditCards);
    }

    private void showAdminCreditCardsOfBank() {
        int bankCode = ui.getIntInputValue("Enter bank code:");
        List<CreditCard> creditCards = bankManager.getAllCreditCardsOfBank(bankCode);
        ui.showListWithTitle("Credit Cards of Bank (" + bankCode + ")", creditCards);
    }

    private void showAdminReactiveCreditCard() {
        String cardNumber = ui.getStringInputValue("Enter card number:");
        if (bankManager.reactivateCreditCard(cardNumber))
            ui.showInfoMessage("Credit card reactivated with card number: " + cardNumber);
        else
            ui.showInvalidInputMessage();
    }

    private void showAdminCloseAccount() {
        long accountNumber = ui.getLongInputValue("Enter account number:");
        if (bankManager.closeAccount(accountNumber))
            ui.showInfoMessage("Account closed with account number: " + accountNumber);
        else
            ui.showInvalidInputMessage();
    }

    private void showAdminReopenAccount() {
        long accountNumber = ui.getLongInputValue("Enter account number:");
        if (bankManager.reopenAccount(accountNumber))
            ui.showInfoMessage("Account reopened with account number: " + accountNumber);
        else
            ui.showInvalidInputMessage();
    }

    private void showAdminTransactionsList() {
        List<Transaction> transactions = bankManager.getAllTransactions();
        ui.showListWithTitle("Transactions List", transactions);
    }

    private void showAdminTransactionsOfCustomer() {
        long nationalId = ui.getLongInputValue("Enter customer national id:");
        List<Transaction> transactions = bankManager.getAllTransactionsOfCustomer(nationalId);
        ui.showListWithTitle("Transactions of Customer (" + nationalId + ")", transactions);
    }

    private void showAdminTransactionsOfBank() {
        int bankCode = ui.getIntInputValue("Enter bank code:");
        List<Transaction> transactions = bankManager.getAllTransactionsOfBank(bankCode);
        ui.showListWithTitle("Transactions of Bank (" + bankCode + ")", transactions);
    }

    private void showAdminTransactionsOfAccount() {
        long accountNumber = ui.getLongInputValue("Enter account number:");
        List<Transaction> transactions = bankManager.getAllTransactionsOfAccount(accountNumber);
        ui.showListWithTitle("Transactions of Account (" + accountNumber + ")", transactions);
    }

    private void showAdminTransactionsOfCreditCard() {
        String cardNumber = ui.getStringInputValue("Enter credit card number:");
        List<Transaction> transactions = bankManager.getAllTransactionsOfCreditCard(cardNumber);
        ui.showListWithTitle("Transactions of Credit Card (" + cardNumber + ")", transactions);
    }

    private void loginAsCustomer() {
        long nationalId = ui.getLongInputValue("Enter national id:");
        if (Validator.validateId(nationalId)) {
            Customer customer = getCustomerById(nationalId);
            if (customer == null)
                showNewCustomer(nationalId);
            showCustomerSelectBank(nationalId);
        } else
            ui.showInvalidInputMessage();
    }

    private Customer getCustomerById(long nationalId) {
        try {
            return bankManager.getCustomerById(nationalId);
        } catch (ServiceException e) {
            ui.showLog(e.getCause().getMessage());
            return null;
        }
    }

    private void showNewCustomer(long nationalId) {
        ui.showTitle("Register New Customer");
        String name = ui.getStringInputValue("Enter your name:");
        Customer customer = new Customer(nationalId, name);
        bankManager.addCustomer(customer);
        ui.showInfoMessage("New customer created with national id: " + nationalId);
    }

    private void showCustomerBanksList() {
        List<Bank> banks = bankManager.getAllBanksList();
        ui.showSelectableList(banks);
    }

    private void showCustomerSelectBank(long nationalId) {
        showCustomerBanksList();
        int bankCode = ui.getIntInputValue("Enter bank code:");
        Bank bank = null;
        try {
            bank = bankManager.getBankByCode(bankCode);
        } catch (ServiceException e) {
            ui.showLog(e.getMessage());
        }
        if (bank != null) {
            showCustomerPage(bankCode, nationalId);
        } else {
            ui.showInvalidInputMessage();
            showCustomerSelectBank(nationalId);
        }
    }

    private void showCustomerPage(int bankCode, long nationalId) {
        ui.showCustomerMenu();
        switch (ui.getIntInputValue()) {
            case 1:
                showCustomerNewAccount(nationalId, bankCode);
                break;
            case 2:
                showCustomerAccounts(nationalId, bankCode);
                break;
            case 3:
                showCustomerNewCreditCard(nationalId, bankCode);
                break;
            case 4:
                showCustomerCreditCards(nationalId, bankCode);
                break;
            case 5:
                showCustomerDeposit(nationalId, bankCode);
                break;
            case 6:
                showCustomerWithdraw(nationalId, bankCode);
                break;
            case 7:
                showInternalTransfer(nationalId, bankCode);
                break;
            case 8:
                showExternalTransfer(bankCode);
                break;
            case 9:
                showCardToCardTransfer(nationalId);
                break;
            case 10:
                showCustomerAllTransactions(nationalId, bankCode);
                break;
            case 11:
                showIbanOfAccount();
                break;
            case 12:
                showBuyRechargingCode(nationalId, bankCode);
                break;
            case 13:
                showChangeFirstPassword(nationalId, bankCode);
                break;
            case 14:
                showChangeSecondPassword(nationalId, bankCode);
                break;
            case 15:
                return;
            default:
                ui.showInvalidInputMessage();
        }
        showCustomerPage(bankCode, nationalId);
    }

    private void showCustomerNewAccount(long nationalId, int bankCode) {
        long accountNumber = BankRandomDataProducer.getRandomAccountNumber();
        Account newAccount = new Account(accountNumber, nationalId, bankCode);
        bankManager.addAccount(newAccount);
        ui.showInfoMessage("New account created with account number: " + accountNumber);
    }

    private void showCustomerAccounts(long nationalId, int bankCode) {
        List<Account> accounts = bankManager.getAllAccountsOfCustomerInBank(nationalId, bankCode);
        ui.showListWithTitle("Customer Accounts List", accounts);
    }

    private void showCustomerNewCreditCard(long nationalId, int bankCode) {
        long accountNumber = ui.getLongInputValue("Enter account number:");
        List<Account> accounts = bankManager.getAllAccountsOfCustomerInBank(nationalId, bankCode);
        Account account = accounts.stream().filter(a -> a.getNumber() == accountNumber).findFirst().orElse(null);
        if (account != null) {
            CreditCard creditCard = null;
            try {
                creditCard = bankManager.getCreditCardByAccountNumber(accountNumber);
            } catch (ServiceException e) {
                ui.showLog(e.getMessage());
            }
            if (creditCard == null) {
                String cardNumber = BankRandomDataProducer.getRandomCardNumberForAccount(account.getNumber(), bankCode);
                ExpireDate expireDate = BankRandomDataProducer.getRandomExpireDate();
                int cvv = BankRandomDataProducer.getRandomCvv();
                String firstPassword = BankRandomDataProducer.getRandomPassword();
                CreditCard newCreditCard = new CreditCard(cardNumber, expireDate, cvv, firstPassword, accountNumber);
                bankManager.addCreditCard(newCreditCard);
                ui.showInfoMessage("Credit card is created with number: " + cardNumber +
                        "\nPlease remember password: " + newCreditCard.getFirstPassword());
            } else
                ui.showInfoMessage("Credit card is already exists.");
        } else
            ui.showInvalidInputMessage();
    }

    private void showCustomerCreditCards(long nationalId, int bankCode) {
        List<CreditCard> creditCards = bankManager.getAllCreditCardsOfCustomerInBank(nationalId, bankCode);
        ui.showListWithTitle("Credit Cards of Customer", creditCards);
    }

    private void showCustomerDeposit(long nationalId, int bankCode) {
        long accountNumber = ui.getLongInputValue("Enter account number:");
        double amount = ui.getDoubleInputValue("Enter amount of deposit:");
        DepositTransaction deposit = new DepositTransaction(accountNumber, amount);
        String message = bankManager.addDepositTransaction(nationalId, bankCode, deposit);
        ui.showInfoMessage(message);
    }

    private void showCustomerWithdraw(long nationalId, int bankCode) {
        long accountNumber = ui.getLongInputValue("Enter account number:");
        double amount = ui.getDoubleInputValue("Enter amount of withdraw:");
        WithdrawTransaction withdraw = new WithdrawTransaction(accountNumber, amount);
        String message = bankManager.addWithdrawTransaction(nationalId, bankCode, withdraw);
        ui.showInfoMessage(message);
    }

    private void showInternalTransfer(long nationalId, int bankCode) {
        long sourceAccountNumber = ui.getLongInputValue("Enter source account number:");
        long destinationAccountNumber = ui.getLongInputValue("Enter destination account number:");
        double amount = ui.getDoubleInputValue("Enter amount of internal transfer:");
        InternalTransaction transaction = new InternalTransaction(sourceAccountNumber, destinationAccountNumber, amount);
        String message = bankManager.addInternalTransaction(nationalId, bankCode, transaction);
        ui.showInfoMessage(message);
    }

    private void showExternalTransfer(int bankCode) {
        String ibanSource = ui.getStringInputValue("Enter source IBAN id: ");
        String ibanDestination = ui.getStringInputValue("Enter destination IBAN id: ");
        double amount = ui.getDoubleInputValue("Enter amount of external transfer:");
        long sourceAccountNumber = Iban.convertToAccountNumber(ibanSource);
        long destinationAccountNumber = Iban.convertToAccountNumber(ibanDestination);

        ExternalTransaction transaction = new ExternalTransaction(Iban.of(sourceAccountNumber, bankCode), Iban.of(destinationAccountNumber, bankCode), amount);
        String message = bankManager.addExternalTransaction(transaction);
        ui.showInfoMessage(message);
    }

    private void showCardToCardTransfer(long nationalId) {
        String sourceCardNumber = ui.getStringInputValue("Enter source card number:");
        String destinationCardNumber = ui.getStringInputValue("Enter destination card number:");
        double amount = ui.getDoubleInputValue("Enter amount of card to card transfer:");
        try {
            CreditCard sourceCard = bankManager.getCustomerCreditCardByCardNumber(nationalId, sourceCardNumber);
            if (sourceCard.isActive() == false) {
                ui.showInfoMessage("credit card is deactivated");
                return;
            }
            CreditCard destinationCard = bankManager.getCreditCardByCardNumber(destinationCardNumber);
            int numberOfWrongPassword = 3;
            while (numberOfWrongPassword > 0) {
                String firstPassword = ui.getStringInputValue("Enter credit card first password:");
                if (sourceCard.getFirstPassword().equals(firstPassword)) {
                    CardToCardTransaction transaction = new CardToCardTransaction(sourceCard, destinationCard, amount);
                    String message = bankManager.addCardToCardTransaction(transaction);
                    ui.showInfoMessage(message);
                    break;
                }
                ui.showLog("Wrong password");
                numberOfWrongPassword--;
            }
            if (numberOfWrongPassword == 0) {
                sourceCard.setInactive();
                ui.showInfoMessage("max number of wrong password. card is deactivated.");
            }

        } catch (ServiceException e) {
            ui.showLog(e.getMessage());
        }
    }

    private void showCustomerAllTransactions(long nationalId, int bankCode) {
        List<Transaction> transactions = bankManager.getAllTransactionsOfCustomerInBank(nationalId, bankCode);
        ui.showListWithTitle("Transactions of Customer:", transactions);
    }

    private void showIbanOfAccount() {
        long accountNumber = ui.getLongInputValue("Enter account number:");
        try {
            Account account = bankManager.getAccountByNumber(accountNumber);
            ui.showInfoMessage(account.getIban());
        } catch (ServiceException e) {
            ui.showInfoMessage(e.getMessage());
        }
    }

    private void showBuyRechargingCode(long nationalId, int bankCode) {
        String cardNumber = ui.getStringInputValue("Enter credit card number:");
        try {
            CreditCard creditCard = bankManager.getCustomerCreditCardByCardNumber(nationalId, cardNumber);
            int numberOfWrongPassword = 3;
            while (numberOfWrongPassword > 0) {
                String firstPassword = ui.getStringInputValue("Enter credit card first password:");
                if (firstPassword.equals(creditCard.getFirstPassword())) {
                    if (creditCard.getSecondPassword() == null) {
                        ui.showInfoMessage("Change 2nd password of card and try again.");
                        break;
                    }
                    String secondPassword = ui.getStringInputValue("Enter credit card second password");
                    int cvv = ui.getIntInputValue("Enter credit card CVV:");
                    int year = ui.getIntInputValue("Enter credit card expire year:");
                    int month = ui.getIntInputValue("Enter credit card expire month:");
                    int amount = ui.getIntInputValue("Enter amount of charge (ex.10000): ");
                    ExpireDate date = ExpireDate.of(year, month);
                    if (secondPassword.equals(creditCard.getSecondPassword())
                            && cvv == creditCard.getCvv()
                            && date.equals(creditCard.getExpireDate())) {
                        RechargingTransaction transaction = new RechargingTransaction(creditCard.getAccountNumber(), amount);
                        String chargeCode = bankManager.addRechargingTransaction(transaction);
                        ui.showInfoMessage("Charging Code: " + chargeCode);
                    } else
                        ui.showInfoMessage("Wrong information of credit card. Try again.");
                    break;
                }
                ui.showLog("Wrong password");
                numberOfWrongPassword--;
            }
        } catch (ServiceException e) {
            ui.showInfoMessage(e.getMessage());
        }
    }

    private void showChangeFirstPassword(long nationalId, int bankCode) {
        String cardNumber = ui.getStringInputValue("Enter credit card number:");
        try {
            CreditCard creditCard = bankManager.getCustomerCreditCardByCardNumber(nationalId, cardNumber);
            int numberOfWrongPassword = 3;
            while (numberOfWrongPassword > 0) {
                String firstPassword = ui.getStringInputValue("Enter credit card first password:");
                if (firstPassword.equals(creditCard.getFirstPassword())) {
                    String newPassword = ui.getValidDigitString("Enter new password:", 4, 4);
                    creditCard.setFirstPassword(newPassword);
                    ui.showInfoMessage("Password changed.");
                    break;
                }
                ui.showLog("Wrong password");
                numberOfWrongPassword--;
            }
        } catch (ServiceException e) {
            ui.showInfoMessage(e.getMessage());
        }
    }

    private void showChangeSecondPassword(long nationalId, int bankCode) {
        String cardNumber = ui.getStringInputValue("Enter credit card number:");
        try {
            CreditCard creditCard = bankManager.getCustomerCreditCardByCardNumber(nationalId, cardNumber);
            int numberOfWrongPassword = 3;
            while (numberOfWrongPassword > 0) {
                String firstPassword = ui.getStringInputValue("Enter credit card first password:");
                if (firstPassword.equals(creditCard.getFirstPassword())) {
                    String secondPassword = ui.getValidDigitString("Enter 4 to 8 digit password:", 4, 8);
                    creditCard.setSecondPassword(secondPassword);
                    ui.showInfoMessage("Password changed.");
                    break;
                }
                ui.showLog("Wrong password");
                numberOfWrongPassword--;
            }
        } catch (ServiceException e) {
            ui.showInfoMessage(e.getMessage());
        }
    }
}
