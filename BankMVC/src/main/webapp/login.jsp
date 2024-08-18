<%--
  Created by IntelliJ IDEA.
  User: DevanK
  Date: 11-08-2024
  Time: 06:06 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<style><%@include file="/WEB-INF/css/login.css"%></style>
<html data-bs-theme="dark">
<head>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>


  <title>Bank Login</title>
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary  ">


<main class="form-signin w-100 m-auto">
  <form action="dashboard" method="post">
    <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

    <div class="form-floating">
      <input type="text" class="form-control" id="floatingInput" placeholder="Username" name="username">
      <label for="floatingInput">Username</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password">
      <label for="floatingPassword">Password</label>
    </div>


    <div class="form-check text-start my-3">
<%--      <input class="form-check-input" type="checkbox" value="remember-me" id="flexCheckDefault" name="remember">
      <label class="form-check-label" for="flexCheckDefault">
        Remember me
      </label>--%>
    </div>

    <button class="btn btn-primary w-100 py-2" type="submit">Sign in</button>

    <p class="mt-5 mb-3  text-danger"><%= request.getAttribute("errorMessage")==null?"":request.getAttribute("errorMessage") %></p>
  </form>
</main>
</body>
</html>
