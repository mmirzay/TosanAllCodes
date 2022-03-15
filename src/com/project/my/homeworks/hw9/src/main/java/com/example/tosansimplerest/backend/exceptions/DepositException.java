package com.example.tosansimplerest.backend.exceptions;

public class DepositException extends Exception {

    public DepositException(String msg) {
        super(msg);
    }

    public DepositException(String msg, Throwable e) {
        super(msg, e);
    }
}
