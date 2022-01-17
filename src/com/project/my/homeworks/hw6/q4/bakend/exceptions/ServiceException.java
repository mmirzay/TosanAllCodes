package com.project.my.homeworks.hw6.q4.bakend.exceptions;

public class ServiceException extends Exception {
    public ServiceException(Throwable e) {
        super(e.getMessage(), e);
    }
}
