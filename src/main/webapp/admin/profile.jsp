<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <h2>Profile</h2>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-8 py-4 shadow" style="padding-left: 10%; padding-right: 10%">

            <button class="btn btn-success ms-auto" onclick="location.href='/user/change-password'">
                Change Password
            </button>

            <table class="table table-borderless mt-3">
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Username</th>
                    <td style="width: 50%">${user.username}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">First Name</th>
                    <td style="width: 50%">${user.firstName}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Last Name</th>
                    <td style="width: 50%">${user.lastName}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Email Address</th>
                    <td style="width: 50%">${user.emailAddress}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Date of Birth</th>
                    <td style="width: 50%">
                        <fmt:formatDate pattern="dd-MM-yyyy" value="${user.dateOfBirth}" />
                    </td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Gender</th>
                    <td style="width: 50%">${user.gender}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Address</th>
                    <td style="width: 50%">${user.address}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Phone Numbers</th>
                    <td style="width: 50%">
                        <c:forEach var="userPhoneNumber" items="${userPhoneNumbers}">
                            <div class="pt-1">${userPhoneNumber.phoneNumber}</div>
                        </c:forEach></td>
                    </td>
                </tr>
            </table>
        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>