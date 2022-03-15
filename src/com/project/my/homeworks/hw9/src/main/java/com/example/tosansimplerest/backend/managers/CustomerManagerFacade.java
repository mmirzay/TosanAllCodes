package com.example.tosansimplerest.backend.managers;

import com.example.tosansimplerest.backend.api.CustomerManagerInterface;
import com.example.tosansimplerest.backend.api.dto.in.CustomerAddParam;
import com.example.tosansimplerest.backend.api.dto.in.CustomerUpdateParam;
import com.example.tosansimplerest.backend.api.dto.out.*;
import com.example.tosansimplerest.backend.entities.Customer;
import com.example.tosansimplerest.backend.exceptions.CustomerException;
import com.example.tosansimplerest.backend.exceptions.ManagerException;
import com.example.tosansimplerest.backend.services.CustomerService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class CustomerManagerFacade implements CustomerManagerInterface {

    private final CustomerService customerService;

    @Override
    public CustomerAddResult addCustomer(CustomerAddParam addParam) {
        try {
            Customer toRegister = createCustomer(addParam);
            Customer registered = customerService.addCustomer(toRegister);
            return new CustomerAddResult(registered.getCode());
        } catch (CustomerException e) {
            throw new ManagerException(e);
        }
    }

    @Override
    public CustomerGetResult getCustomer(Long code) {
        try {
            Customer customer = customerService.getCustomerByCode(code);
            return CustomerGetResult.builder()
                    .fullName(customer.getFullName())
                    .nationalId(customer.getNationalId())
                    .build();
        } catch (CustomerException e) {
            throw new ManagerException(e);
        }
    }

    @Override
    public CustomerDeleteResult deleteCustomer(Long code) {
        try {
            Customer customer = customerService.getCustomerByCode(code);
            customerService.deleteCustomerByCode(customer.getCode());
            return new CustomerDeleteResult(code, customer.getFullName());
        } catch (CustomerException e) {
            throw new ManagerException(e);
        }
    }

    @Override
    public CustomerUpdateResult updateCustomer(Long code, CustomerUpdateParam param) {
        try {
            Customer customer = customerService.getCustomerByCode(code);
            customer.setFullName(param.getFirstName() + " " + param.getLastName());
            String message = customerService.updateCustomer(customer) ? "updated successfully." : "updating failed!";
            return new CustomerUpdateResult(code, message);
        } catch (CustomerException e) {
            throw new ManagerException(e);
        }
    }


    @Override
    public CustomersList getAllCustomersList() {
        try {
            List<Customer> customers = customerService.getAllCustomers();
            if (customers.isEmpty())
                throw new CustomerException("customer list is empty.");
            return new CustomersList(customers);
        } catch (CustomerException e) {
            throw new ManagerException(e);
        }
    }

    private Customer createCustomer(CustomerAddParam addParam) {
        String fullName = addParam.getFirstName() + " " + addParam.getLastName();
        return Customer.of(fullName, addParam.getNationalId());
    }
}
