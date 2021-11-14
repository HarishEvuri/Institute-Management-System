<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <h2>Dashboard</h2>

    <div class="row mt-5">
        <div class="col-lg-4 col-md-6 col-sm-12 px-3 mb-4">
            <div class="card shadow" style="background-color: #6200ea; color: #fff; cursor: pointer;" onclick="location.href='/admin/departments'">
                <div class="card-body">
                    <div class="d-flex">
                        <h3 class="card-title me-auto">Departments</h3>
                        <span class="fa fa-building fa-3x" aria-hidden="true"></span>
                    </div>
                    <p class="card-text" style="opacity: 0.6;">Manage all the departments.</p>
                </div>
            </div>
        </div>

        <div class="col-lg-4 col-md-6 col-sm-12 px-3 mb-4">
            <div class="card shadow" style="background-color: #f4a100; color: #fff; cursor: pointer;" onclick="location.href='/admin/students'">
                <div class="card-body">
                    <div class="d-flex">
                        <h3 class="card-title me-auto">Students</h3>
                        <span class="fa fa-user-graduate fa-3x" aria-hidden="true"></span>
                    </div>
                    <p class="card-text" style="opacity: 0.6;">Manage all the students.</p>
                </div>
            </div>
        </div>

        <div class="col-lg-4 col-md-6 col-sm-12 px-3 mb-4">
            <div class="card shadow" style="background-color: #9c27b0; color: #fff; cursor: pointer;" onclick="location.href='/admin/professors'">
                <div class="card-body">
                    <div class="d-flex">
                        <h3 class="card-title me-auto">Professors</h3>
                        <span class="fa fa-user-tie fa-3x" aria-hidden="true"></span>
                    </div>
                    <p class="card-text" style="opacity: 0.6;">Manage all the professors.</p>
                </div>
            </div>
        </div>

        <div class="col-lg-4 col-md-6 col-sm-12 px-3 mb-4">
            <div class="card shadow" style="background-color: #26a69a; color: #fff; cursor: pointer;" onclick="location.href='/admin/staff'">
                <div class="card-body">
                    <div class="d-flex">
                        <h3 class="card-title me-auto">Staff</h3>
                        <span class="fa fa-user-shield fa-3x" aria-hidden="true"></span>
                    </div>
                    <p class="card-text" style="opacity: 0.6;">Manage all the staff.</p>
                </div>
            </div>
        </div>

        <div class="col-lg-4 col-md-6 col-sm-12 px-3 mb-4">
            <div class="card shadow" style="background-color: #2c3e50; color: #fff; cursor: pointer;" onclick="location.href='/admin/courses'">
                <div class="card-body">
                    <div class="d-flex">
                        <h3 class="card-title me-auto">Courses</h3>
                        <span class="fa fa-book-open fa-3x" aria-hidden="true"></span>
                    </div>
                    <p class="card-text" style="opacity: 0.6;">Manage all the Courses.</p>
                </div>
            </div>
        </div>

        <div class="col-lg-4 col-md-6 col-sm-12 px-3 mb-4">
            <div class="card shadow" style="background-color: #6900c7; color: #fff; cursor: pointer;" onclick="location.href='/admin/sections'">
                <div class="card-body">
                    <div class="d-flex">
                        <h3 class="card-title me-auto">Sections</h3>
                        <span class="fa fa-clipboard-list fa-3x" aria-hidden="true"></span>
                    </div>
                    <p class="card-text" style="opacity: 0.6;">Manage all the Sections.</p>
                </div>
            </div>
        </div>

        <div class="col-lg-4 col-md-6 col-sm-12 px-3 mb-4">
            <div class="card shadow" style="background-color: #f4a100; color: #fff; cursor: pointer;" onclick="location.href='/admin/fee-transactions'">
                <div class="card-body">
                    <div class="d-flex">
                        <h3 class="card-title me-auto">Fee Transactions</h3>
                        <span class="fa fa-coins fa-3x" aria-hidden="true"></span>
                    </div>
                    <p class="card-text" style="opacity: 0.6;">Manage all the fee particulars.</p>
                </div>
            </div>
        </div>

        <div class="col-lg-4 col-md-6 col-sm-12 px-3 mb-4">
            <div class="card shadow" style="background-color: #9c27b0; color: #fff; cursor: pointer;" onclick="location.href='/admin/salary-transactions'">
                <div class="card-body">
                    <div class="d-flex">
                        <h3 class="card-title me-auto">Salary Transactions</h3>
                        <span class="fa fa-money-check fa-3x" aria-hidden="true"></span>
                    </div>
                    <p class="card-text" style="opacity: 0.6;">Manage all the salary transactions.</p>
                </div>
            </div>
        </div>

         <div class="col-lg-4 col-md-6 col-sm-12 px-3 mb-4">
            <div class="card shadow" style="background-color: #1abc9c; color: #fff; cursor: pointer;" onclick="location.href='/admin/complaints'">
                <div class="card-body">
                    <div class="d-flex">
                        <h3 class="card-title me-auto">Complaints</h3>
                        <span class="fa fa-comment-dots fa-3x" aria-hidden="true"></span>
                    </div>
                    <p class="card-text" style="opacity: 0.6;">Manage all the complaints.</p>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/template/footer.jsp" %>