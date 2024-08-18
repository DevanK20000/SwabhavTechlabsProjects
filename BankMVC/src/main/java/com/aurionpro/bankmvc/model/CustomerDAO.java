package com.aurionpro.bankmvc.model;

import com.aurionpro.bankmvc.entity.AccountType;
import com.aurionpro.bankmvc.entity.Customer;
import com.aurionpro.bankmvc.entity.Login;
import com.aurionpro.bankmvc.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomerDAO{
    DbUtils dbUtils =null;
    Connection conn = null;
    private void makeConnection(){
        dbUtils = DbUtils.getDbUtils();
        conn = dbUtils.connectToDb();
    }

    @Override
    public List<Customer> getAllCustomer() {
        List<Customer> customers = new ArrayList<Customer>();
        try {
            makeConnection();
            String sql = "select accounts.customerid, accounts.loginid, login.username, accounts.firstName,accounts.lastName, accounts.email from accounts join login on login.loginid = accounts.loginid";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               customers.add(new Customer(
                       resultSet.getInt(1),
                       resultSet.getInt(2),
                       resultSet.getString(3),
                       resultSet.getString(4),
                       resultSet.getString(5),
                       resultSet.getString(6)
               ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }
}
