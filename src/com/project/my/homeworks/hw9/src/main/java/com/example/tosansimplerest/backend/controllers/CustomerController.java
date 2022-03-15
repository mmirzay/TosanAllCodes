package com.example.tosansimplerest.backend.controllers;

import com.example.tosansimplerest.backend.api.CustomerManagerInterface;
import com.example.tosansimplerest.backend.api.dto.in.CustomerAddParam;
import com.example.tosansimplerest.backend.api.dto.in.CustomerUpdateParam;
import com.example.tosansimplerest.backend.api.dto.out.*;
import com.example.tosansimplerest.backend.exceptions.ManagerException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerManagerInterface manager;

    @PostMapping("/add")
    public ResponseEntity<CustomerAddResult> add(@RequestBody CustomerAddParam addParam) {
        try {
            CustomerAddResult result = manager.addCustomer(addParam);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (ManagerException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @GetMapping("/{code}")
    public ResponseEntity<CustomerGetResult> getCustomerProfile(@PathVariable Long code) {
        try {
            CustomerGetResult result = manager.getCustomer(code);
            return ResponseEntity.ok(result);
        } catch (ManagerException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        }
    }

    @PutMapping("/{code}")
    public ResponseEntity<CustomerUpdateResult> updateProfile(@PathVariable Long code, @RequestBody CustomerUpdateParam param) {
        try {
            CustomerUpdateResult result = manager.updateCustomer(code, param);
            return ResponseEntity.ok(result);
        } catch (ManagerException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }


    @GetMapping
    public ResponseEntity<CustomersList> customersList() {
        try {
            CustomersList result = manager.getAllCustomersList();
            return ResponseEntity.ok(result);
        } catch (ManagerException e) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, e.getLocalizedMessage());
        }
    }

    @DeleteMapping("/customer/{code}")
    public ResponseEntity<CustomerDeleteResult> deleteCustomer(@PathVariable Long code) {
        try {
            CustomerDeleteResult result = manager.deleteCustomer(code);
            return ResponseEntity.ok().body(result);
        } catch (ManagerException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }
}
