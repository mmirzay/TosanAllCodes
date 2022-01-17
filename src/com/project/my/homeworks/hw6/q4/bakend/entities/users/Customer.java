package com.project.my.homeworks.hw6.q4.bakend.entities.users;

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
}
