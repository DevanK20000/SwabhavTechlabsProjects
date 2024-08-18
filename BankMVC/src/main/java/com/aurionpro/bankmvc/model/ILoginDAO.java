package com.aurionpro.bankmvc.model;

import com.aurionpro.bankmvc.entity.Login;

public interface ILoginDAO {
    void insertLogin(Login login);
    void updateLogin(Login login);
    void deleteLogin(Integer userid);
    Login getLoginById(Integer id);

    Login getLoginByUsername(String username);

    boolean checkUsernameExist(String username);
    boolean validateLogin(String username,String password);
}
