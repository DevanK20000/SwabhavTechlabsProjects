package com.aurionpro.bankmvc.model;

import com.aurionpro.bankmvc.entity.Customer;
import com.aurionpro.bankmvc.entity.Transaction;
import com.aurionpro.bankmvc.entity.TransactionType;
import com.aurionpro.bankmvc.utils.DbUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO implements ITransactionDAO{
    DbUtils dbUtils =null;
    Connection conn = null;
    private void makeConnection(){
        dbUtils = DbUtils.getDbUtils();
        conn = dbUtils.connectToDb();
    }

    @Override
    public void insertTransaction(Transaction transaction) {
        try {
            makeConnection();
            String sql = "insert into transations(senderAccountNumber, receiverAccountNumber, type, amount, current_balance, new_balance, date) values(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,transaction.getSenderAccountNumber()==null?null:transaction.getSenderAccountNumber().toString());
            preparedStatement.setString(2,transaction.getReceiverAccountNumber()==null?null:transaction.getReceiverAccountNumber().toString());
            preparedStatement.setString(3,transaction.getTransactionType().name());
            preparedStatement.setDouble(4, transaction.getAmount());
            preparedStatement.setDouble(5,transaction.getCurrent_balance());
            preparedStatement.setDouble(6,transaction.getNew_balance());
            preparedStatement.setDate(7,transaction.getDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions =  new ArrayList<Transaction>();
        try {
            makeConnection();
            String sql = "select * from transations";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
               transactions.add(new Transaction(
                       resultSet.getInt(1),
                       resultSet.getInt(2),
                       resultSet.getInt(3),
                       TransactionType.valueOf(resultSet.getString(4)),
                       resultSet.getDouble(5),
                       resultSet.getDouble(6),
                       resultSet.getDouble(7),
                       resultSet.getDate(8)

               ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }
}
