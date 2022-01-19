package com.project.my.homeworks.hw6.q4.bakend.api;

import com.project.my.homeworks.hw6.q4.bakend.entities.banks.Bank;
import com.project.my.homeworks.hw6.q4.bakend.entities.users.Customer;
import com.project.my.homeworks.hw6.q4.bakend.exceptions.ServiceException;

import java.util.List;

public interface BankManagerInterface {

    List<Bank> getAllBanksList();

    Bank getBankByCode(int bankCode) throws ServiceException;

    void addCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomerById(long nationalId) throws ServiceException;
}
