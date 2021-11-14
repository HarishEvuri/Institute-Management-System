<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <h2>Dashboard</h2>

    <div class="row mt-5">
        <div class="col-md-4 col-sm-6 px-3 mb-4">
            <div class="card shadow" style="background-color: #6200ea; color: #fff; cursor: pointer;" onclick="location.href='/staff/staff'">
                <div class="card-body">
                    <div class="d-flex">
                        <h3 class="card-title me-auto">Staff</h3>
                        <span class="fa fa-user-shield fa-3x" aria-hidden="true"></span>
                    </div>
                    <p class="card-text" style="opacity: 0.6;">View all the staff.</p>
                </div>
            </div>
        </div>

        <div class="col-md-4 col-sm-6 px-3 mb-4">
            <div class="card shadow" style="background-color: #9c27b0; color: #fff; cursor: pointer;" onclick="location.href='/staff/salary-transactions'">
                <div class="card-body">
                    <div class="d-flex">
                        <h3 class="card-title me-auto">Transactions</h3>
                        <span class="fa fa-money-check fa-3x" aria-hidden="true"></span>
                    </div>
                    <p class="card-text" style="opacity: 0.6;">View your transactions.</p>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/template/footer.jsp" %>