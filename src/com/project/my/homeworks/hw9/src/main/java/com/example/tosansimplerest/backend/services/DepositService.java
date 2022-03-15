package com.example.tosansimplerest.backend.services;

import com.example.tosansimplerest.backend.entities.Customer;
import com.example.tosansimplerest.backend.entities.Deposit;
import com.example.tosansimplerest.backend.exceptions.DepositException;
import com.example.tosansimplerest.backend.repositories.DepositRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepositService {
    private final DepositRepository repository;

    public Deposit addDeposit(Deposit deposit) throws DepositException {
        if (deposit.getBalance() < 0)
            throw new DepositException("Invalid balance.");
        try {
            return repository.save(deposit);
        } catch (DataIntegrityViolationException e) {
            if (e.getRootCause() instanceof SQLIntegrityConstraintViolationException)
                throw new DepositException("Duplicate deposit number");
            throw new DepositException("Some thing wrong while adding/updating new deposit", e);
        }
    }

    public List<Deposit> getAllDeposits() {
        return repository.findAll();
    }

    public List<Deposit> getAllDepositsOfCustomer(Customer owner) {
        return repository.findAllByOwner(owner);
    }

    public Deposit getDepositByNumber(long number) throws DepositException {
        return repository.findByNumber(number).orElseThrow(() -> new DepositException("Deposit number is not exists."));
    }

    public Deposit getDepositById(Long id) throws DepositException {
        return repository.findById(id).orElseThrow(() -> new DepositException("Deposit Id is not exists."));
    }

    public boolean updateDeposit(Deposit deposit) throws DepositException {
        if (deposit.getId() == null || repository.findById(deposit.getId()).isPresent() == false)
            return false;
        this.addDeposit(deposit);
        return true;
    }

    public boolean deleteDepositById(Long id) {
        if (id == null || repository.findById(id).isPresent() == false)
            return false;
        repository.deleteById(id);
        return true;
    }
}
