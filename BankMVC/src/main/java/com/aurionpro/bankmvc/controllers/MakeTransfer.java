package com.aurionpro.bankmvc.controllers;

import com.aurionpro.bankmvc.entity.BankAccount;
import com.aurionpro.bankmvc.entity.BankAccountType;
import com.aurionpro.bankmvc.entity.Transaction;
import com.aurionpro.bankmvc.entity.TransactionType;
import com.aurionpro.bankmvc.model.BankAccountDAO;
import com.aurionpro.bankmvc.model.BankAccountService;
import com.aurionpro.bankmvc.model.TransactionDAO;
import com.aurionpro.bankmvc.model.TransactionService;

import java.io.*;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "MakeTransferServlet", value = "/MakeTransfer")
public class MakeTransfer extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("dashboard_customer_transfer.jsp");
        TransactionType transactionType = TransactionType.valueOf(request.getParameter("transferType"));
        System.out.println(transactionType.name());

        BankAccount bankAccount = (BankAccount) session.getAttribute("selectedBankAccount");
        double amount = Double.parseDouble(request.getParameter("amount"));

        TransactionService transactionService = new TransactionService(new TransactionDAO());
        BankAccountService bankAccountService = new BankAccountService(new BankAccountDAO());

        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        if(amount<1){
            request.setAttribute("error","Amount Should be greater than zero ");
            try {
                requestDispatcher.forward(request,response);
                return;
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }
        if(((transactionType.equals(TransactionType.transfer) || transactionType.equals(TransactionType.debit))
                && bankAccount.getBankAccountType().equals(BankAccountType.saving)
                && amount > (bankAccount.getBalance() - bankAccount.getMin_overdue_limit()))){
            request.setAttribute("error","Amount Exceeds minimum balance criteria");
            try {
                requestDispatcher.forward(request,response);
                return;
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }

        if ((transactionType.equals(TransactionType.transfer) || transactionType.equals(TransactionType.debit))
                && bankAccount.getBankAccountType().equals(BankAccountType.current)
                && amount > (bankAccount.getBalance() + bankAccount.getMin_overdue_limit())) {

            request.setAttribute("error","Amount Exceeds maximum allowed over due limit");
            try {
                requestDispatcher.forward(request,response);
                return;
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }


        switch (transactionType){
            case transfer:
                int receiverAccountNumber = Integer.parseInt(request.getParameter("receiverAccountNumber"));
                BankAccount reciverBankAccount = bankAccountService.getBankAccountById(receiverAccountNumber);
                transactionService.insertTransaction(new Transaction(
                        0,bankAccount.getAccountNumber(),receiverAccountNumber,transactionType,amount, bankAccount.getBalance(), (bankAccount.getBalance()-amount),sqlDate
                ));
                bankAccount.debit(amount);
                reciverBankAccount.credit(amount);
                bankAccountService.updateBankAccount(reciverBankAccount);
                break;
            case credit:
                transactionService.insertTransaction(new Transaction(
                        0,null, bankAccount.getAccountNumber(), transactionType,amount, bankAccount.getBalance(), (bankAccount.getBalance()+amount),sqlDate
                ));
                bankAccount.credit(amount);
                break;
            case debit:
                transactionService.insertTransaction(new Transaction(
                        0,bankAccount.getAccountNumber(),null,transactionType,amount, bankAccount.getBalance(), (bankAccount.getBalance()-amount),sqlDate
                ));
                bankAccount.debit(amount);
                break;
        }

        bankAccountService.updateBankAccount(bankAccount);
        request.setAttribute("moneyTransferred","Transaction Successful");
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