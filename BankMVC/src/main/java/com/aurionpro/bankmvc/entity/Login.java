package com.aurionpro.bankmvc.entity;

public class Login {
    private Integer loginID;
    private String username;
    private String password;
    private AccountType accountType;

    public Login(Integer loginID, String username, String password,AccountType accountType) {
        this.loginID = loginID;
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }

    public Integer getLoginID() {
        return loginID;
    }

    public void setLoginID(Integer loginID) {
        this.loginID = loginID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "loginID=" + loginID +
                ", username='" + username + '\'' +
                ", accountType=" + accountType +
                '}';
    }
}
