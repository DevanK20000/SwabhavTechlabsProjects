package com.aurionpro.bankmvc.model;

import com.aurionpro.bankmvc.entity.Account;
import com.aurionpro.bankmvc.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountDAO implements IAccountDAO {
    DbUtils dbUtils =null;
    Connection conn = null;

    private void makeConnection(){
        dbUtils = DbUtils.getDbUtils();
        conn = dbUtils.connectToDb();
    }

    @Override
    public void insertAccount(Account account) {
        try {
            makeConnection();
            String sql = "insert into accounts (loginid, firstName, lastName, email) values(?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,account.getLoginId());
            preparedStatement.setString(2,account.getFirstName());
            preparedStatement.setString(3,account.getLastName());
            preparedStatement.setString(4,account.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            makeConnection();
            String sql = "update accounts set loginid=?, firstName=?, lastName=? where customerid=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,account.getLoginId());
            preparedStatement.setString(2,account.getFirstName());
            preparedStatement.setString(3, account.getLastName());
            preparedStatement.setInt(4,account.getCustomerId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAccount(Integer accountId) {

    }

    @Override
    public Account getAccountById(Integer customerId) {
       Account account = null;
        try {
            makeConnection();
            String sql = "select * from accounts where customerid=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                account = new Account(resultSet.getInt(1),
                                      resultSet.getInt(2),
                                      resultSet.getString(3),
                                      resultSet.getString(4),
                                      resultSet.getString(5)
                        );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       return account;
    }

    @Override
    public List<Account> getAllAccount() {
        return null;
    }

    public Account getAccountByLoginId(Integer loginId) {
        Account account = null;
        try {
            makeConnection();
            String sql = "select * from accounts where loginid=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,loginId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                account = new Account(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }
}
