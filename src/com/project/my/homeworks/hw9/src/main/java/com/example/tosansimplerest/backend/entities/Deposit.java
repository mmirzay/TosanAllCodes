package com.example.tosansimplerest.backend.entities;

import com.example.tosansimplerest.backend.utilities.DepositNumberGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private long number;
    private Status status;
    private double balance;
    @ManyToOne(optional = false)
    Customer owner;

    private Deposit(long number, Status status, double balance, Customer owner) {
        this.number = number;
        this.status = status;
        this.balance = balance;
        this.owner = owner;
    }

    public static Deposit of(Customer owner) {
        return of(owner, 0);
    }

    public static Deposit of(Customer owner, double balance) {
        long number = DepositNumberGenerator.getRandomAccountNumber();
        Status status = Status.OPENED;
        return new Deposit(number, status, balance, owner);
    }
}
