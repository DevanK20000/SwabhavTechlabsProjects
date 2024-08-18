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

@WebServlet(name = "ProfileEditorServlet", value = "/ProfileEditor")
public class ProfileEditor extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String username = String.valueOf(session.getAttribute("username"));
        LoginService loginService = new LoginService(new LoginDAO());
        Integer loginId= loginService.getLoginIdByUsername(username);
        AccountService accountService = new AccountService(new AccountDAO());
        Account account= accountService.getAccountByLoginId(loginId);
        Login login = loginService.getLoginById(loginId);

        request.setAttribute("account",account);
        request.setAttribute("login",login);

        session.setAttribute("account",account);
        session.setAttribute("login",login);
        System.out.println(account);
        System.out.println(login);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("dashboard_profile.jsp");
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