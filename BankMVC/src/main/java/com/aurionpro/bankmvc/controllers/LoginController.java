package com.aurionpro.bankmvc.controllers;

import com.aurionpro.bankmvc.entity.AccountType;
import com.aurionpro.bankmvc.model.LoginDAO;
import com.aurionpro.bankmvc.model.LoginService;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "LoginControllerServlet", value = "/dashboard")
public class LoginController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Getting attributes
        String username =  request.getParameter("username");
        String password = request.getParameter("password");
//        String remember =  request.getParameter("remember");

        LoginService loginService = new LoginService(new LoginDAO());

        // Mock validation of user credentials
        try {
            if (loginService.validateLogin(username,password)) {
                // Credentials are valid; create a session
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setMaxInactiveInterval(30 * 60); // 30 minutes session timeout

                // Set account type
                AccountType accountType = loginService.getLoginType(username);
                session.setAttribute("accountType",accountType);

                // Forward to a protected page
                if(accountType.equals(AccountType.admin)){
                    RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard_admin_createAccount.jsp");
                    dispatcher.forward(request, response);
                }

                if(accountType.equals(AccountType.customer)){
                    RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard_customer_accountSelector.jsp");
                    dispatcher.forward(request, response);
                }


            } else {
                // Credentials are invalid; forward back to login page with an error message
                request.setAttribute("errorMessage", "Invalid username or password");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
        }catch (ServletException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    public void destroy() {
    }
}