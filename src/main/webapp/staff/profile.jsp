<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <h2>Profile</h2>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">

            <button class="btn btn-success ms-auto" onclick="location.href='/user/change-password'">
                Change Password
            </button>
            
            <div class="row mt-3">

                <div class="col-lg-6">
                    <table class="table table-borderless mb-0">
                        <tr>
                            <th style="width: 40%">Username</th>
                            <td style="width: 60%">${user.username}</td>
                        </tr>
                        <tr>
                            <th style="width: 40%">First Name</th>
                            <td style="width: 60%">${user.firstName}</td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Last Name</th>
                            <td style="width: 60%">${user.lastName}</td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Email Address</th>
                            <td style="width: 60%">${user.emailAddress}</td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Date of Birth</th>
                            <td style="width: 60%">
                                <fmt:formatDate pattern="dd-MM-yyyy" value="${user.dateOfBirth}" />
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Gender</th>
                            <td style="width: 60%">${user.gender}</td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Address</th>
                            <td style="width: 60%">${user.address}</td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Phone Numbers</th>
                            <td style="width: 60%">
                                <c:forEach var="userPhoneNumber" items="${userPhoneNumbers}">
                                    <div class="pt-1">${userPhoneNumber.phoneNumber}</div>
                                </c:forEach></td>
                            </td>
                        </tr>
                    </table>
                </div>

                <div class="col-lg-6">
                    <table class="table table-borderless">
                        <tr>
                            <th style="width: 40%">Staff Id</th>
                            <td style="width: 60%">${staff.staffId}</td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Designation</th>
                            <td style="width: 60%">${staff.designation}</td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Employee Id</th>
                            <td style="width: 60%">${employee.employeeId}</td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Join Date</th>
                            <td style="width: 60%">
                                <fmt:formatDate pattern="dd-MM-yyyy" value="${employee.joinDate}" />
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">End Date</th>
                            <td style="width: 60%">
                                <fmt:formatDate pattern="dd-MM-yyyy" value="${employee.endDate}" />
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Account Number</th>
                            <td style="width: 60%">${employee.accountNumber}</td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Bank IFSC</th>
                            <td style="width: 60%">${employee.bank_IFSC_code}</td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Pan Number</th>
                            <td style="width: 60%">${employee.panNumber}</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>