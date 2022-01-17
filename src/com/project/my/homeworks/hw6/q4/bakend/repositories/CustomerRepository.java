package com.project.my.homeworks.hw6.q4.bakend.repositories;

import com.project.my.homeworks.hw6.q4.bakend.entities.users.Customer;
import com.project.my.homeworks.hw6.q4.bakend.exceptions.CustomerException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerRepository {
    private Set<Customer> customers;

    public CustomerRepository() {
        customers = new HashSet<>();
    }

    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> fetchAllCustomers() {
        return new ArrayList<>(customers);
    }

    public Customer fetchCustomerById(long nationalId) throws CustomerException {
        return customers.stream()
                .filter(c -> c.getNationalId() == nationalId)
                .findFirst().orElseThrow(() -> new CustomerException("customer id is not exist"));
    }

    public boolean updateCustomer(Customer customer) {
        try {
            Customer toUpdate = fetchCustomerById(customer.getNationalId());
            toUpdate.setName(customer.getName());
            return true;
        } catch (CustomerException e) {
            System.out.println("LogError: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteCustomerById(long nationalId) {
        try {
            Customer toRemove = fetchCustomerById(nationalId);
            return customers.remove(toRemove);
        } catch (CustomerException e) {
            System.out.println("LogError: " + e.getMessage());
            return false;
        }
    }
}
