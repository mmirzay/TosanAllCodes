package com.example.tosansimplerest.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Deposit {
    private String number;
    private Status status;
    private double balance;
}
