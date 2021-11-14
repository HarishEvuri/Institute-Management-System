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
            <div class="card shadow" style="background-color: #6200ea; color: #fff; cursor: pointer;" onclick="location.href='/student/enrollments'">
                <div class="card-body">
                    <div class="d-flex">
                        <h3 class="card-title me-auto">Enrollments</h3>
                        <span class="fa fa-clipboard-list fa-3x" aria-hidden="true"></span>
                    </div>
                    <p class="card-text" style="opacity: 0.6;">View your enrollments.</p>
                </div>
            </div>
        </div>

        <div class="col-md-4 col-sm-6 px-3 mb-4">
            <div class="card shadow" style="background-color: #f4a100; color: #fff; cursor: pointer;" onclick="location.href='/student/departments'">
                <div class="card-body">
                    <div class="d-flex">
                        <h3 class="card-title me-auto">Departments</h3>
                        <span class="fa fa-building fa-3x" aria-hidden="true"></span>
                    </div>
                    <p class="card-text" style="opacity: 0.6;">View all the departments.</p>
                </div>
            </div>
        </div>

        <div class="col-md-4 col-sm-6 px-3 mb-4">
            <div class="card shadow" style="background-color: #9c27b0; color: #fff; cursor: pointer;" onclick="location.href='/student/students'">
                <div class="card-body">
                    <div class="d-flex">
                        <h3 class="card-title me-auto">Students</h3>
                        <span class="fa fa-user-graduate fa-3x" aria-hidden="true"></span>
                    </div>
                    <p class="card-text" style="opacity: 0.6;">View all the students.</p>
                </div>
            </div>
        </div>

        <div class="col-md-4 col-sm-6 px-3 mb-4">
            <div class="card shadow" style="background-color: #26a69a; color: #fff; cursor: pointer;" onclick="location.href='/student/courses'">
                <div class="card-body">
                    <div class="d-flex">
                        <h3 class="card-title me-auto">Courses</h3>
                        <span class="fa fa-book-open fa-3x" aria-hidden="true"></span>
                    </div>
                    <p class="card-text" style="opacity: 0.6;">View all the courses.</p>
                </div>
            </div>
        </div>

        <div class="col-md-4 col-sm-6 px-3 mb-4">
            <div class="card shadow" style="background-color: #2c3e50; color: #fff; cursor: pointer;" onclick="location.href='/student/fee-transactions'">
                <div class="card-body">
                    <div class="d-flex">
                        <h3 class="card-title me-auto">Transactions</h3>
                        <span class="fa fa-money-check fa-3x" aria-hidden="true"></span>
                    </div>
                    <p class="card-text" style="opacity: 0.6;">View your fee transactions.</p>
                </div>
            </div>
        </div>

        <div class="col-md-4 col-sm-6 px-3 mb-4">
            <div class="card shadow" style="background-color: #6900c7; color: #fff; cursor: pointer;" onclick="location.href='/student/complaints'">
                <div class="card-body">
                    <div class="d-flex">
                        <h3 class="card-title me-auto">Complaints</h3>
                        <span class="fa fa-comment-dots fa-3x" aria-hidden="true"></span>
                    </div>
                    <p class="card-text" style="opacity: 0.6;">Manage your complaints.</p>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/template/footer.jsp" %>