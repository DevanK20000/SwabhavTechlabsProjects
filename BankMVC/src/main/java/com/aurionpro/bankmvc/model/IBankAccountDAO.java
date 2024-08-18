package com.aurionpro.bankmvc.model;

import com.aurionpro.bankmvc.entity.BankAccount;

import java.util.List;

public interface IBankAccountDAO {
    void insertBankAccount(BankAccount bankAccount);
    void updateBankAccount(BankAccount bankAccount);
    void deleteBankAccount(Integer accountNumber);
    BankAccount getBankAccountById(Integer accountNumber);
    List<BankAccount> getAllBankAccounts();
}
