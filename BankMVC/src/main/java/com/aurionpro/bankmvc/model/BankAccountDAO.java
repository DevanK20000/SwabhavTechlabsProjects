package com.aurionpro.bankmvc.model;

import com.aurionpro.bankmvc.entity.Account;
import com.aurionpro.bankmvc.entity.BankAccount;
import com.aurionpro.bankmvc.entity.BankAccountType;
import com.aurionpro.bankmvc.entity.Customer;
import com.aurionpro.bankmvc.utils.DbUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDAO implements IBankAccountDAO{
    DbUtils dbUtils =null;
    Connection conn = null;

    private void makeConnection(){
        dbUtils = DbUtils.getDbUtils();
        conn = dbUtils.connectToDb();
    }


    @Override
    public void insertBankAccount(BankAccount bankAccount) {
        try {
            makeConnection();
            String sql = "insert into bankaccount (customerid, AccountType, balance, min_overdue_limits) values(?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,bankAccount.getCustomerId());
            preparedStatement.setString(2,bankAccount.getBankAccountType().name());
            preparedStatement.setDouble(3,bankAccount.getBalance());
            preparedStatement.setDouble(4,bankAccount.getMin_overdue_limit());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBankAccount(BankAccount bankAccount) {
        try {
            makeConnection();
            String sql = "update bankaccount set balance=? where AccountNumber=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDouble(1,bankAccount.getBalance());
            preparedStatement.setInt(2,bankAccount.getAccountNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBankAccount(Integer accountNumber) {

    }

    @Override
    public BankAccount getBankAccountById(Integer accountNumber) {
        BankAccount bankAccount=null;
        try {
            makeConnection();
            String sql = "select * from bankaccount where accountNumber=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,accountNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                bankAccount = new BankAccount(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        BankAccountType.valueOf(resultSet.getString(3)),
                        resultSet.getDouble(4),
                        resultSet.getDouble(5)
                );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bankAccount;
    }

    @Override
    public List<BankAccount> getAllBankAccounts() {
        List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
        try {
            makeConnection();
            String sql = "select * from bankaccount";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                bankAccounts.add(new BankAccount(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        BankAccountType.valueOf(resultSet.getString(3)),
                        resultSet.getDouble(4),
                        resultSet.getDouble(5)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bankAccounts;
    }
}
