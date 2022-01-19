package com.project.my.homeworks.hw6.q4.bakend.services;

import com.project.my.homeworks.hw6.q4.bakend.api.BankManagerInterface;
import com.project.my.homeworks.hw6.q4.bakend.entities.banks.Bank;
import com.project.my.homeworks.hw6.q4.bakend.entities.users.Customer;
import com.project.my.homeworks.hw6.q4.bakend.exceptions.BankException;
import com.project.my.homeworks.hw6.q4.bakend.exceptions.CustomerException;
import com.project.my.homeworks.hw6.q4.bakend.exceptions.ServiceException;
import com.project.my.homeworks.hw6.q4.bakend.repositories.*;

import java.util.List;

public class BankManagerService implements BankManagerInterface {
    private final BankRepository bankRepository = new BankRepository();
    private final CustomerRepository customerRepository = new CustomerRepository();

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
}