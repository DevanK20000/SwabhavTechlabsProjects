package com.aurionpro.bankmvc.model;

import com.aurionpro.bankmvc.entity.Customer;

import java.util.List;

public interface ICustomerDAO {
    List<Customer> getAllCustomer();
}
