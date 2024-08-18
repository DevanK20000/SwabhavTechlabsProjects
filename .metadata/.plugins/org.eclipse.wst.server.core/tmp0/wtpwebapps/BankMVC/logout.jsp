<%--
  Created by IntelliJ IDEA.
  User: DevanK
  Date: 11-08-2024
  Time: 10:39 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.*, javax.servlet.*" %>
<%
  session = request.getSession(false);
  if (session != null) {
    session.invalidate(); // Invalidate the session
  }
  RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
  dispatcher.forward(request, response); // Forward to login page
%>
