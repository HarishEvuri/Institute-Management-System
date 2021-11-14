<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp" %>

<div class="container my-4 px-3">
  <h2>Login</h2>
  <div class="row justify-content-center">
    <div class="card mt-4 col-lg-7 p-0 shadow">
      <div class="row">
        <div class="col-md-6 d-none d-md-block p-0">
          <img 
            src="/img/login.jpg"
            alt="login form"
            class="rounded"
            width="100%"
            height="450"
          />
        </div>

        <div class="col-md-6 d-flex align-items-center justify-content-center p-3">
          <form action="/user/login" method="post">
                <div class="d-flex justify-content-center mb-3">
                  <span class="h2 fw-bold mb-0">SIT</span>
                </div>
                
                <c:if test="${not empty success}">
                  <div class="alert alert-success" role="alert">
                      ${success}
                  </div>
                </c:if>
                 
                 <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                      ${error}
                    </div>
                 </c:if>

                <h5 class="fw-normal mb-4" style="letter-spacing: 1px">
                  Sign into your account
                </h5>

                <div class="form-outline mb-3">
                  <input
                    type="text"
                    class="form-control form-control-lg"
                    id="username"
                    placeholder="Username"
                    name="username"
                    required
                  />
                </div>
                <div class="form-outline mb-3">
                  <input
                    type="password"
                    class="form-control form-control-lg"
                    id="password"
                    placeholder="Password"
                    name="password"
                    required
                  />
                </div>

                <input
                  type="hidden"
                  name="${_csrf.parameterName}"
                  value="${_csrf.token}"
                />

                <div class="pt-1 mb-3">
                  <button class="btn btn-dark btn-block" type="submit">
                    Log In
                  </button>
                </div>

                <a class="small text-muted" href="/user/forgot-password">Forgot password?</a>
              </form>
        </div>
      </div>
    </div>
  </div>
</div>

<%@ include file="footer.jsp" %>
