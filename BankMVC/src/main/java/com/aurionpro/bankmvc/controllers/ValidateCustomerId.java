package com.aurionpro.bankmvc.controllers;

import com.aurionpro.bankmvc.entity.Account;
import com.aurionpro.bankmvc.entity.Login;
import com.aurionpro.bankmvc.model.AccountDAO;
import com.aurionpro.bankmvc.model.AccountService;
import com.aurionpro.bankmvc.model.LoginDAO;
import com.aurionpro.bankmvc.model.LoginService;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ValidateCustomerIdServlet", value = "/ValidateCustomerId")
public class ValidateCustomerId extends HttpServlet {


    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        int customerId =Integer.parseInt(request.getParameter("customerId"));
        AccountService accountService = new AccountService(new AccountDAO());
        if(accountService.accountExists(customerId)){
            request.setAttribute("disableForm", false);
            request.setAttribute("disableFormCustomerId", true);
            request.setAttribute("customerId",customerId);
            Account account = accountService.getAccountById(customerId);
            request.setAttribute("firstName",account.getFirstName());
            request.setAttribute("lastName",account.getLastName());
            request.setAttribute("email",account.getEmail());

            LoginService loginService = new LoginService(new LoginDAO());
            Login login = loginService.getLoginById(account.getLoginId());
            request.setAttribute("username",login.getUsername());

            RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard_admin_createBankAccount.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("BankAccountForm");
            try {
                dispatcher.forward(request,response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
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