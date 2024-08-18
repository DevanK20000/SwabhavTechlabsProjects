package com.aurionpro.bankmvc.model;

import com.aurionpro.bankmvc.entity.Login;
import com.aurionpro.bankmvc.entity.AccountType;

public class LoginService {
    private LoginDAO loginDAO;

    public LoginService(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    public void insertLogin(Login login){
        loginDAO.insertLogin(login);
    }

    public void updateLogin(Login login){
        loginDAO.updateLogin(login);
    }

    public Login getLoginById(int loginId){
        return  loginDAO.getLoginById(loginId);
    }

    public Integer getLoginIdByUsername(String username){
        return  loginDAO.getLoginByUsername(username).getLoginID();
    }

    public boolean validateLogin(String username, String password){
        return loginDAO.validateLogin(username,password);
    }

    public AccountType getLoginType(String username){
        Login login = loginDAO.getLoginByUsername(username);
        return login.getAccountType();
    }

    public boolean usernameExists(String username){
        Login login = loginDAO.getLoginByUsername(username);
        return login != null;
    }
}
