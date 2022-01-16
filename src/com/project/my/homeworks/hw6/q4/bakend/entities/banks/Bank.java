package com.project.my.homeworks.hw6.q4.bakend.entities.banks;

public class Bank {
    private String name;
    private int code;

    public Bank(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}
