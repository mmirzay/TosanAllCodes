package com.example.tosansimplerest.backend.repositories;

import com.example.tosansimplerest.backend.entities.Customer;
import com.example.tosansimplerest.backend.entities.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    Optional<Deposit> findByNumber(long number);
    List<Deposit> findAllByOwner(Customer owner);
}
