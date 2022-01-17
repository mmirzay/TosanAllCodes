package com.project.my.homeworks.hw6.q4.bakend.entities.users;

import java.util.Objects;

public class Customer {
    private long nationalId;
    private String name;

    public Customer(long nationalId, String name) {
        this.nationalId = nationalId;
        this.name = name;
    }

    public long getNationalId() {
        return nationalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return nationalId == customer.nationalId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nationalId);
    }

    @Override
    public String toString() {
        return "Customer " + name + " nationalID: " + nationalId;
    }
}
