<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="role">${pageContext.request.userPrincipal.principal.user.role.toLowerCase()}</c:set>

<!DOCTYPE html>
<html class="h-100">
  <head>
    <meta charset="ISO-8859-1" />
    <link rel="icon" type="image/png" href="/img/icon.png">
    <title>SIT</title>
    <!-- CSS only -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />

    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap5.min.css" rel="stylesheet">
    <link href="/css/main.css" rel="stylesheet">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/682da6449a.js"></script>
  </head>

  <body class="d-flex flex-column h-100 scroll" style="background-color: #f5f5f5">
    <nav class="navbar navbar-expand-md navbar-dark bg-dark" style="padding-top: 0.3rem; padding-bottom: 0.3rem">
      <div class="container">
        <a class="navbar-brand mb-0 h1" href="/">
          <img src="/img/logo.png" alt="img" height="45" width="auto">
        </a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav me-auto">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="/">Home</a>
            </li>
          </ul>

          <c:choose>
              <c:when test="${not empty pageContext.request.userPrincipal}">
                  <ul class="navbar-nav me-2">
                    <li class="na-item">
                      <a class="nav-link active" href="/welcome">Dashboard</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" href="/${role}/profile">Profile</a>
                    </li>
                  </ul>

                  <!-- Button trigger modal -->
                  <button type="button" class="btn btn-success px-3 me-2" data-bs-toggle="modal" data-bs-target="#logoutModal">
                    Logout
                  </button>

                  <!-- Modal -->
                  <div class="modal fade" id="logoutModal" tabindex="-1" aria-labelledby="logoutModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                      <div class="modal-content">
                        <div class="modal-header">
                          <h5 class="modal-title" id="logoutModalLabel">Logout</h5>
                          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                          Are you sure want to logout ?
                        </div>
                        <div class="modal-footer">
                          <button type="button" class="btn btn-light" data-bs-dismiss="modal">Cancel</button>
                          <a href="/user/logout" class="btn btn-primary" role="button">Logout</a>
                        </div>
                      </div>
                    </div>
                  </div>
              </c:when>
              <c:otherwise>
                  <a href="/user/login" class="btn btn-success px-3 me-2" role="button">Login</a>
              </c:otherwise>
          </c:choose>
      
        </div>
      </div>
    </nav>