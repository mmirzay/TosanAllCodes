package com.project.my.homeworks.hw6.q4.bakend.entities.accounts;

public enum IbanType {
    DEPOSIT(0), FACILITY(1);

    int value;
    IbanType(int value) {
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
