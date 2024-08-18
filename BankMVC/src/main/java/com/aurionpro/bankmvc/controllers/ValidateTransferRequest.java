package com.aurionpro.bankmvc.controllers;

import com.aurionpro.bankmvc.entity.BankAccount;
import com.aurionpro.bankmvc.entity.TransactionType;
import com.aurionpro.bankmvc.model.BankAccountDAO;
import com.aurionpro.bankmvc.model.BankAccountService;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ValidateTransferRequestServlet", value = "/ValidateTransferRequest")
public class ValidateTransferRequest extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("dashboard_customer_transfer.jsp");
        TransactionType transactionType = TransactionType.valueOf(request.getParameter("transferType"));
        Integer receiverAccountNumber = Integer.parseInt(request.getParameter("receiverAccountNumber").isEmpty()?"-1":request.getParameter("receiverAccountNumber"));

        if(transactionType.equals(TransactionType.transfer)) {
            BankAccountService bankAccountService = new BankAccountService(new BankAccountDAO());
            BankAccount bankAccount = bankAccountService.getBankAccountById(receiverAccountNumber);
            if(bankAccount==null){
                try {
                    request.setAttribute("error","Invalid Account Number");
                    requestDispatcher.forward(request,response);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }
            }
            request.setAttribute("receiverAccountNumber",receiverAccountNumber);
        }

        request.setAttribute("disableForm",false);
        request.setAttribute("disableTransferType",true);
        request.setAttribute("transferType",transactionType);
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