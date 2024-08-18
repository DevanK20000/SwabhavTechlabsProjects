package com.aurionpro.bankmvc.controllers;

import com.aurionpro.bankmvc.entity.Transaction;
import com.aurionpro.bankmvc.model.TransactionDAO;
import com.aurionpro.bankmvc.model.TransactionService;

import java.io.*;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "TransactionListServlet", value = "/TransactionList")
public class TransactionList extends HttpServlet {
   List<Transaction> transactions;

    public void init() {
        TransactionService transactionService = new TransactionService(new TransactionDAO());
        transactions = transactionService.getAllTransactions();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("transactions",transactions);
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard_admin_transactions.jsp");
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