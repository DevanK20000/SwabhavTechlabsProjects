package com.aurionpro.bankmvc.entity;

public class BankAccount {
    private int accountNumber;
    private int customerId;
    private BankAccountType bankAccountType;
    private Double balance;
    private Double min_overdue_limit;

    public BankAccount(int accountNumber, int customerId, BankAccountType bankAccountType, Double balance, Double min_overdue_limit) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.bankAccountType = bankAccountType;
        this.balance = balance;
        this.min_overdue_limit = min_overdue_limit;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public BankAccountType getBankAccountType() {
        return bankAccountType;
    }

    public void setBankAccountType(BankAccountType bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getMin_overdue_limit() {
        return min_overdue_limit;
    }

    public void setMin_overdue_limit(Double min_overdue_limit) {
        this.min_overdue_limit = min_overdue_limit;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber=" + accountNumber +
                ", customerId=" + customerId +
                ", bankAccountType=" + bankAccountType +
                ", balance=" + balance +
                ", min_overdue_limit=" + min_overdue_limit +
                '}';
    }

    public void credit(Double amount){
        setBalance(getBalance()+amount);
    }
    public void debit(Double amount){
        setBalance(getBalance()-amount);
    }
}
