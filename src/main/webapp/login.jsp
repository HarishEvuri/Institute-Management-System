<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>Insert title here</title>
  </head>
  <body>
    <form action="/login" method="post">
      <input
        id="username"
        type="text"
        name="username"
        value=""
        placeholder="Username"
        required
      />
      <input
        id="password"
        type="password"
        name="password"
        placeholder="Password"
        required
      />
      <input
        type="hidden"
        name="${_csrf.parameterName}"
        value="${_csrf.token}"
      />
      <button type="submit">LogIn</button>
    </form>
    <h2>Login Page</h2>
  </body>
</html>
