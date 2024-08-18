package com.aurionpro.bankmvc.model;

import com.aurionpro.bankmvc.entity.Customer;

import java.util.List;

public class CustomerService {
    CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAllCustomers(){
        return this.customerDAO.getAllCustomer();
    }
}
