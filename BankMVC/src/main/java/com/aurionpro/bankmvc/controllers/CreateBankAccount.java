package com.aurionpro.bankmvc.controllers;

import com.aurionpro.bankmvc.entity.BankAccount;
import com.aurionpro.bankmvc.entity.BankAccountType;
import com.aurionpro.bankmvc.model.BankAccountDAO;
import com.aurionpro.bankmvc.model.BankAccountService;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "CreateBankAccountServlet", value = "/CreateBankAccount")
public class CreateBankAccount extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BankAccountService bankAccountService = new BankAccountService(new BankAccountDAO());

        int customerId = Integer.parseInt(request.getParameter("customerId"));
        BankAccountType bankAccountType = BankAccountType.valueOf(request.getParameter("bankAccountType"));
        Double balance = 1000.0;
        Double limit = 1000.0;

        bankAccountService.insertBankAccount(new BankAccount(0, customerId, bankAccountType,balance,limit));
        request.setAttribute("accountCreated","Bank account successfully created");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("dashboard_admin_createBankAccount.jsp");
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

    }

    public void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void destroy() {
    }
}