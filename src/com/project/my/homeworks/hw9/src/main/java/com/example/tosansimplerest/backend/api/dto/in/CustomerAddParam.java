package com.example.tosansimplerest.backend.api.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddParam {
    private String firstName;
    private String lastName;
    private Long nationalId;
}
