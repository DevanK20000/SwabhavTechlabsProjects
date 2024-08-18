package com.aurionpro.bankmvc.controllers;

import com.aurionpro.bankmvc.entity.BankAccount;
import com.aurionpro.bankmvc.entity.Transaction;
import com.aurionpro.bankmvc.model.TransactionDAO;
import com.aurionpro.bankmvc.model.TransactionService;

import java.io.*;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "PassbookViewServlet", value = "/PassbookView")
public class PassbookView extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        BankAccount bankAccount = (BankAccount) session.getAttribute("selectedBankAccount");
        if (bankAccount==null){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("BankAccountSelector");
            try {
                requestDispatcher.forward(request,response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }

        TransactionService transactionService = new TransactionService(new TransactionDAO());
        List<Transaction> transactions = transactionService.getAllTransactionsOfAccountNumber(bankAccount.getAccountNumber());
        request.setAttribute("transactions",transactions);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("dashboard_customer_Passport.jsp");
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