package com.example.tosansimplerest.backend.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private String fullName;
    @Column(nullable = false, unique = true)
    private Long nationalId;

    private Customer(String fullName, Long nationalId) {
        this.fullName = fullName;
        this.nationalId = nationalId;
    }

    public static Customer of(String fullName, Long nationalId) {
        return new Customer(fullName, nationalId);
    }
}
