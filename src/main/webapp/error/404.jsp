<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/template/header.jsp" %>

<div class="container-fluid custom-container">
    <div class="div text-center" style="color: red;">
        The requested URL <script>document.write(window.location.pathname);</script> was not found on this server.
    </div>
</div>

<%@ include file="/template/footer.jsp" %>