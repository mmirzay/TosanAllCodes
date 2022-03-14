package com.example.tosansimplerest.backend.repositories;

import com.example.tosansimplerest.backend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCode(Long code);

    Optional<Customer> findByNationalId(Long code);
}
