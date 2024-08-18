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

@WebServlet(name = "EditProfileFormServlet", value = "/EditProfileForm")
public class EditProfileForm extends HttpServlet {
    private String message;

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        Login login = (Login) session.getAttribute("login");

        String newPassword = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        if(firstName==null|| firstName.isEmpty()){
            request.setAttribute("error","Needs First Name");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("dashboard_profile.jsp");
            try {
                requestDispatcher.forward(request,response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }

        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setEmail(email);
        AccountService accountService =  new AccountService(new AccountDAO());
        accountService.updateAccount(account);

        if(newPassword!=null || !newPassword.isEmpty()){
            login.setPassword(newPassword);
            LoginService loginService = new LoginService(new LoginDAO());
            loginService.updateLogin(login);
        }
        request.setAttribute("error","Profile Updated");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ProfileEditor");
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