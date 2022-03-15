package com.example.tosansimplerest.backend.api;

import com.example.tosansimplerest.backend.api.dto.in.CustomerAddParam;
import com.example.tosansimplerest.backend.api.dto.in.CustomerUpdateParam;
import com.example.tosansimplerest.backend.api.dto.out.*;

public interface CustomerManagerInterface {

    CustomerAddResult addCustomer(CustomerAddParam addParam);

    CustomerGetResult getCustomer(Long code);

    CustomerDeleteResult deleteCustomer(Long code);

    CustomerUpdateResult updateCustomer(Long code, CustomerUpdateParam param);

    CustomersList getAllCustomersList();

}
