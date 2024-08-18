<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<style><%@include file="/WEB-INF/css/cover.css"%></style>
<!DOCTYPE html>
<html>
<head>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

  <!-- Custom styles for this template -->
  <link href="WEB-INF/css/cover.css" rel="stylesheet">

  <title>Welcome to Banking</title>

</head>
<body class=" d-flex h-100 text-center text-bg-dark">
<div class="container text-light">

  <div class="d-flex flex-column min-vh-100 justify-content-between">
    <nav class="navbar nav-masthead navbar-expand">
      <div class="container-fluid d-flex flex-column flex-sm-row">
        <a class="navbar-brand link-light fs-2" href="#"> Banking </a>
        <div class="navbar-nav d-flex gap-3">
          <a href="#" class="nav-link active link-light fw-bold"> Home </a>
          <a href="#" class="nav-link hover link-secondary fw-bold"> About  </a>
<%--          <a href="#" class="nav-link hover link-secondary fw-bold"> Contact </a>--%>
        </div>
      </div>
    </nav>

    <main class="d-flex flex-column align-items-center gap-3">
      <h1 class="h1"> Banking Application </h1>
      <p class="lead text-center"> Banking application created using JAVA EE, mysql and tomcat server, Designed using Bootstrap, MVC architecture applied. </p>
      <button class="btn btn-light btn-lg" onclick="window.location='login.jsp'"> SIGN IN </button>
    </main>

    <footer class="text-center mb-3 text-secondary">
      <div>
        Application by
        <span class="text-light"> Devan Khandagale </span>
      </div>
    </footer>
  </div>

</div>

</body>
</html>