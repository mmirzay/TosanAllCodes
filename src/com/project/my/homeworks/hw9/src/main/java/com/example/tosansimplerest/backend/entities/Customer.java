package com.example.tosansimplerest.backend.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    private String fullName;
    private String nationalId;
    private String code;
}
