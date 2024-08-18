package com.aurionpro.bankmvc.controllers;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "BankAccountFormServlet", value = "/BankAccountForm")
public class BankAccountForm extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        request.setAttribute("disableForm", true);
        request.setAttribute("disableFormCustomerId", false);
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard_admin_createBankAccount.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doGet(request, response);
    }

    public void destroy() {
    }
}