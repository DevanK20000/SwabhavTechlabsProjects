package com.aurionpro.bankmvc.model;

import com.aurionpro.bankmvc.entity.BankAccount;
import com.aurionpro.bankmvc.entity.BankAccountType;

import java.util.List;
import java.util.stream.Collectors;

public class BankAccountService {
    BankAccountDAO bankAccountDAO;

    public BankAccountService(BankAccountDAO bankAccountDAO) {
        this.bankAccountDAO = bankAccountDAO;
    }

    public void  insertBankAccount(BankAccount bankAccount){
        bankAccountDAO.insertBankAccount(bankAccount);
    }

    public void updateBankAccount(BankAccount bankAccount){
        bankAccountDAO.updateBankAccount(bankAccount);
    }

    public BankAccount getBankAccountById(Integer accountNumber){
        return bankAccountDAO.getBankAccountById(accountNumber);
    }

    public List<BankAccount> getAllBankAccounts(){
        return  bankAccountDAO.getAllBankAccounts();
    }

    public List<BankAccount> getAllBankAccountsByCustomerId(Integer customerId){
        return  bankAccountDAO.getAllBankAccounts().stream().filter(bankAccount -> bankAccount.getCustomerId()==customerId).collect(Collectors.toList());
    }
}
