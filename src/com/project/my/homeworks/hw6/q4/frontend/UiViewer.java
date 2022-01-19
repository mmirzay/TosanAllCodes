package com.project.my.homeworks.hw6.q4.frontend;

import com.project.my.homeworks.hw6.q4.utilities.Input;
import com.project.my.homeworks.hw6.q4.utilities.Printer;

import java.util.List;

public class UiViewer {
    public void showMainMenu() {
        System.out.println("1- login as admin");
        System.out.println("2- login as customer");
        System.out.println("3- exit");
    }

    public void showAdminMenu() {
        System.out.println("1- show all banks list");
        System.out.println("2- show all customers list");
        System.out.println("3- show all accounts list");
        System.out.println("4- show all credit cards list");
        System.out.println("5- show accounts of customer");
        System.out.println("6- show accounts of bank");
        System.out.println("7- show credit card of customer");
        System.out.println("8- show credit card of bank");
        System.out.println("9- reactive credit card");
        System.out.println("10- close an account");
        System.out.println("11- reopen an account");
        System.out.println("12- show all transactions list");
        System.out.println("13- show transactions of customer");
        System.out.println("14- show transactions of bank");
        System.out.println("15- show transactions of account");
        System.out.println("16- show transactions of credit card");
        System.out.println("17- back");
    }

    public void showCustomerMenu() {
        System.out.println("1- request new account");
        System.out.println("2- show accounts");
        System.out.println("3- request credit card for account");
        System.out.println("4- show credit cards info");
        System.out.println("5- deposit");
        System.out.println("6- withdraw");
        System.out.println("7- internal transfer by account number");
        System.out.println("8- external transfer by account's IBAN id");
        System.out.println("9- card to card");
        System.out.println("10- show all transactions");
        System.out.println("11- show IBAN of account");
        System.out.println("12- buy phone recharge code");
        System.out.println("13- change 1st password");
        System.out.println("14- change 2nd password");
        System.out.println("15- back");
    }

    public int getIntInputValue() {
        return getIntInputValue("->");
    }

    public int getIntInputValue(String msg) {
        return Input.getIntInputValue(msg);
    }

    public long getLongInputValue(String msg) {
        return Input.getLongInputValue(msg);
    }

    public double getDoubleInputValue(String msg) {
        return Input.getDoubleInputValue(msg);
    }

    public void showInvalidInputMessage() {
        Printer.printInvalidInputMessage();
    }

    public String getOptionalStringInputValue(String string) {
        return Input.getOptionalStringInputValue(string);
    }

    public String getStringInputValue(String string) {
        return Input.getStringInputValue(string);
    }

    public String getValidDigitString(String msg, int minLength, int maxLength) {
        return Input.getValidDigitString(msg, minLength, maxLength);
    }

    public void showInfoMessage(String msg) {
        Printer.printInfoMessage(msg);
    }

    public void showLineMessage(String msg) {
        Printer.printLineMessage(msg);
    }

    public void showTitle(String string) {
        Printer.printTitle(string);
    }

    public void showLog(String string) {
        Printer.printLogMessage(string);
    }

    public <T> void showListWithTitle(String title, List<T> list) {
        showTitle(title);
        showList(list, false);
    }

    private <T> void showList(List<T> list, boolean isSelectable) {
        if (list == null || list.isEmpty()) {
            Printer.printErrorMessage("List is empty");
            return;
        }
        int counter = 0;
        for (T t : list)
            System.out.println("#" + (++counter) + ": " + t);
        Printer.printLongSeperatorLine();
        if (isSelectable == false)
            Printer.printWaitingMessage();
    }

    public <T> void showSelectableList(List<T> list) {
        showList(list, true);
    }

}
