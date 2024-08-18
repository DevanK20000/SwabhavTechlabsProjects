package com.aurionpro.bankmvc.model;

import com.aurionpro.bankmvc.entity.AccountType;
import com.aurionpro.bankmvc.entity.Login;
import com.aurionpro.bankmvc.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO implements ILoginDAO{
    DbUtils dbUtils =null;
    Connection conn = null;

    private void makeConnection(){
        dbUtils = DbUtils.getDbUtils();
        conn = dbUtils.connectToDb();
    }


    @Override
    public void insertLogin(Login login) {
        try {
            makeConnection();
            String sql = "insert into login (username,password,type) values(?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, login.getUsername());
            preparedStatement.setString(2,login.getPassword());
            preparedStatement.setString(3,login.getAccountType().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateLogin(Login login) {
        try {
            makeConnection();
            String sql = "update login SET username=?, password=?, type=? where loginid=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,login.getUsername());
            preparedStatement.setString(2,login.getUsername());
            preparedStatement.setString(3,login.getAccountType().name());
            preparedStatement.setInt(4,login.getLoginID());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteLogin(Integer userid) {

    }

    @Override
    public Login getLoginById(Integer id) {
        Login login = null;
        try {
            makeConnection();
            String sql = "select * from login where loginid=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (AccountType.customer.name().equals(resultSet.getString("type"))) {
                    login = new Login(resultSet.getInt("loginid"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            AccountType.customer);
                }

                if (AccountType.admin.name().equals(resultSet.getString("type"))) {
                    login = new Login(resultSet.getInt("loginid"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            AccountType.admin);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return login;
    }
    @Override
    public Login getLoginByUsername(String username) {
        Login login = null;
        try {
            makeConnection();
            String sql = "select * from login where username=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (AccountType.customer.name().equals(resultSet.getString("type"))) {
                    login = new Login(resultSet.getInt("loginid"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            AccountType.customer);
                }

                if (AccountType.admin.name().equals(resultSet.getString("type"))) {
                    login = new Login(resultSet.getInt("loginid"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            AccountType.admin);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return login;
    }
    @Override
    public boolean checkUsernameExist(String username) {
        return false;
    }

    @Override
    public boolean validateLogin(String username, String password) {
        try {
            makeConnection();
            String sql = "select username,password,type from login where (username=? and password=?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                if(resultSet.getString("username").equals(username) && resultSet.getString("password").equals(password) ){
                    return true;
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
