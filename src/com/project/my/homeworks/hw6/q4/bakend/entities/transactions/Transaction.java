package com.project.my.homeworks.hw6.q4.bakend.entities.transactions;

import java.util.Date;

public abstract class Transaction {

    private Long srcAccountNumber;
    private Long destAccountNumber;
    private double amount;
    private Date date;
    private TransactionStatus status;
    private TransactionMessage message;

    public Transaction(Long srcAccountNumber, Long destAccountNumber, double amount) {
        this.srcAccountNumber = srcAccountNumber;
        this.destAccountNumber = destAccountNumber;
        this.amount = amount;
        this.date = new Date();
    }

    public Long getSourceAccountNumber() {
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

    public TransactionMessage getMessage() {
        return message;
    }

    public void setMessage(TransactionMessage message) {
        this.message = message;
        status = message == TransactionMessage.VALID_TRANSACTION? TransactionStatus.SUCCESSFUL : TransactionStatus.FAILED;
    }
}
