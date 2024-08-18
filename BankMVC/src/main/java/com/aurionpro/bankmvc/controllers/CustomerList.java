package com.aurionpro.bankmvc.controllers;

import com.aurionpro.bankmvc.entity.Customer;
import com.aurionpro.bankmvc.model.CustomerDAO;
import com.aurionpro.bankmvc.model.CustomerService;

import java.io.*;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "CustomerListServlet", value = "/CustomerList")
public class CustomerList extends HttpServlet {
    List<Customer> customers;

    public void init() {
        CustomerService customerService = new CustomerService(new CustomerDAO());
        customers = customerService.getAllCustomers();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("customers",customers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard_admin_customers.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }


    }
    
    public void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       doGet(request,response);
    }

    public void destroy() {
    }
}