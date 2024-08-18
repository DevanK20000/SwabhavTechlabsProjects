package com.aurionpro.bankmvc.model;

import com.aurionpro.bankmvc.entity.Account;

import java.util.List;

public interface IAccountDAO {
    void insertAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(Integer accountId);
    Account getAccountById(Integer accountId);
    List<Account> getAllAccount();
}
