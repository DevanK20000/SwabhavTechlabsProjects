package com.aurionpro.bankmvc.controllers;

import com.aurionpro.bankmvc.entity.Account;
import com.aurionpro.bankmvc.entity.AccountType;
import com.aurionpro.bankmvc.entity.Login;
import com.aurionpro.bankmvc.model.AccountDAO;
import com.aurionpro.bankmvc.model.AccountService;
import com.aurionpro.bankmvc.model.LoginDAO;
import com.aurionpro.bankmvc.model.LoginService;
import com.aurionpro.bankmvc.utils.DbUtils;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "CreateAccountServlet", value = "/CreateAccount")
public class CreateAccount extends HttpServlet {


    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username= request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        AccountType accountType = AccountType.valueOf(request.getParameter("accountType"));
        String errors = "";


        LoginService loginService = new LoginService(new LoginDAO());
        if(loginService.usernameExists(username))
            errors+="Username Already exists<br>";
        if(password==null ||password.length()<4)
            errors+="Password should at least be 4 character long<br>";
        if(firstName==null || firstName.equals(" ") || firstName.isEmpty())
            errors+="Invalid First Name<br>";

        if(!email.isEmpty()){
            Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            Matcher emailMatcher = emailPattern.matcher(email);
            if (!emailMatcher.matches())
                errors += "Invalid Email";
        }

        if(!errors.isEmpty()){
        try{
            request.setAttribute("errorMessage", errors);
            RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard_admin_createAccount.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }}
        else {
            loginService.insertLogin(new Login(0, username, password, accountType));
            Integer loginId = loginService.getLoginIdByUsername(username);
            AccountService accountService = new AccountService(new AccountDAO());
            accountService.insertAccount(new Account(
                    0, loginId, firstName, lastName, email
            ));
            try {
                request.setAttribute("accountCreated", "Account Created");
                RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard_admin_createAccount.jsp");
                dispatcher.forward(request, response);
            }catch (ServletException e) {
                throw new RuntimeException(e);
            }

        }

    }
    
    public void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       doGet(request,response);
    }

    public void destroy() {
    }
}