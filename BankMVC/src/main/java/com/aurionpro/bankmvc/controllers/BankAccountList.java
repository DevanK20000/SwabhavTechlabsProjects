package com.aurionpro.bankmvc.controllers;

import com.aurionpro.bankmvc.entity.BankAccount;
import com.aurionpro.bankmvc.model.BankAccountDAO;
import com.aurionpro.bankmvc.model.BankAccountService;

import java.io.*;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "BankAccountListServlet", value = "/BankAccountList")
public class BankAccountList extends HttpServlet {
    List<BankAccount> bankAccounts;

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BankAccountService bankAccountService= new BankAccountService(new BankAccountDAO());
        bankAccounts = bankAccountService.getAllBankAccounts();
        request.setAttribute("bankAccounts",bankAccounts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard_admin_bankAccounts.jsp");
        try {
            dispatcher.forward(request,response);
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