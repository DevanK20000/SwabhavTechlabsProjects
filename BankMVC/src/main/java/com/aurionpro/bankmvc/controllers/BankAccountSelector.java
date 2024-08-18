package com.aurionpro.bankmvc.controllers;

import com.aurionpro.bankmvc.entity.Account;
import com.aurionpro.bankmvc.entity.BankAccount;
import com.aurionpro.bankmvc.entity.Login;
import com.aurionpro.bankmvc.model.*;

import java.io.*;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "BankAccountSelectorServlet", value = "/BankAccountSelector")
public class BankAccountSelector extends HttpServlet {
    List<BankAccount> bankAccounts;

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        BankAccountService bankAccountService = new BankAccountService(new BankAccountDAO());
        LoginService loginService = new LoginService(new LoginDAO());
        Integer loginId =  loginService.getLoginIdByUsername(String.valueOf(session.getAttribute("username")));
        AccountService accountService = new AccountService(new AccountDAO());
        Account account =  accountService.getAccountByLoginId(loginId);

        bankAccounts = bankAccountService.getAllBankAccountsByCustomerId(account.getCustomerId());
        request.setAttribute("bankAccounts",bankAccounts);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("dashboard_customer_accountSelector.jsp");
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