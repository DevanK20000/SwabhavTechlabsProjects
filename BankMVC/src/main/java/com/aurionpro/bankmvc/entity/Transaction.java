package com.aurionpro.bankmvc.entity;

import java.sql.Date;

public class Transaction {
    private int transactionId;
    private Integer senderAccountNumber;
    private Integer receiverAccountNumber;
    private TransactionType transactionType;
    private Double amount;
    private Double current_balance;
    private Double new_balance;
    private Date date;

    public Transaction(int transactionId, Integer senderAccountNumber, Integer receiverAccountNumber, TransactionType transactionType, Double amount, Double current_balance, Double new_balance, Date date) {
        this.transactionId = transactionId;
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.current_balance = current_balance;
        this.new_balance = new_balance;
        this.date = date;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(int senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public Integer getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public void setReceiverAccountNumber(int receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getCurrent_balance() {
        return current_balance;
    }

    public void setCurrent_balance(Double current_balance) {
        this.current_balance = current_balance;
    }

    public Double getNew_balance() {
        return new_balance;
    }

    public void setNew_balance(Double new_balance) {
        this.new_balance = new_balance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", senderAccountNumber=" + senderAccountNumber +
                ", receiverAccountNumber=" + receiverAccountNumber +
                ", transactionType=" + transactionType +
                ", amount=" + amount +
                ", current_balance=" + current_balance +
                ", new_balance=" + new_balance +
                ", date=" + date +
                '}';
    }
}
