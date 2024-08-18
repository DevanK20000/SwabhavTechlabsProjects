package com.aurionpro.bankmvc.model;

import com.aurionpro.bankmvc.entity.Account;

public class AccountService {
    AccountDAO accountDAO = new AccountDAO();

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account getAccountById(int customerId){
        return accountDAO.getAccountById(customerId);
    }

    public void insertAccount(Account account){
        accountDAO.insertAccount(account);
    }
    public boolean accountExists(int customerId){
        return accountDAO.getAccountById(customerId)!=null;
    }
    public  Account getAccountByLoginId(Integer loginId){
        return accountDAO.getAccountByLoginId(loginId);
    }
    public void updateAccount(Account account){
        accountDAO.updateAccount(account);
    }
}
