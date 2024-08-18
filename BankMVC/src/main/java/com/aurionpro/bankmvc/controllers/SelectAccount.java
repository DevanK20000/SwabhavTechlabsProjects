package com.aurionpro.bankmvc.controllers;

import com.aurionpro.bankmvc.entity.AccountType;
import com.aurionpro.bankmvc.entity.BankAccount;
import com.aurionpro.bankmvc.model.BankAccountDAO;
import com.aurionpro.bankmvc.model.BankAccountService;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "SelectAccountServlet", value = "/SelectAccount")
public class SelectAccount extends HttpServlet {


    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Integer accountNumber = Integer.parseInt(request.getParameter("accountSelected"));
        BankAccountService bankAccountService =  new BankAccountService(new BankAccountDAO());
        BankAccount bankAccount = bankAccountService.getBankAccountById(accountNumber);
        session.setAttribute("selectedBankAccount",bankAccount);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("BankAccountSelector");
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