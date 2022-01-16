package com.project.my.homeworks.hw6.q4.bakend.entities.transactions;

import java.util.Date;

public abstract class Transaction {

    private long srcAccountNumber;
    private Long destAccountNumber;
    private double amount;
    private Date date;
    private TransactionStatus status;
    private String description;

    public Transaction(long srcAccountNumber, Long destAccountNumber, double amount) {
        this.srcAccountNumber = srcAccountNumber;
        this.destAccountNumber = destAccountNumber;
        this.amount = amount;
        this.date = new Date();
    }

    public long getSourceAccountNumber() {
        return srcAccountNumber;
    }

    public Long getDestinationAccountNumber() {
        return destAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
