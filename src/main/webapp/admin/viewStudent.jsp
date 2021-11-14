<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <div class="d-flex align-items-center">
        <h2 style="cursor : pointer;" onclick="location.href='/admin/students'">Students  >  </h2>
        <h3 class="ms-2">${student.rollNumber}</h3>
    </div>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">
            
            <div class="d-flex ms-auto">
                <button class="btn btn-primary me-2"  onclick="location.href='/admin/students/${student.rollNumber}/edit'">Edit Student</button>
                <button class="btn btn-success me-2"  onclick="location.href='/admin/user/${user.username}/phone'">Manage Phone</button>
                <button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">Delete Student</button>

                <!-- Modal -->
                <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteModalLabel">Delete Student</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Are you sure want to delete ?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-bs-dismiss="modal">Cancel</button>
                        <button class="btn btn-danger" onclick="location.href='/admin/students/${user.username}/delete'">Delete</button>
                    </div>
                    </div>
                </div>
                </div>
            </div>

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
                                <c:forEach var="studentPhoneNumber" items="${studentPhoneNumbers}">
                                    <div class="pt-1">${studentPhoneNumber.phoneNumber}</div>
                                </c:forEach></td>
                            </td>
                        </tr>
                    </table>
                </div>

                <div class="col-lg-6">
                    <table class="table table-borderless">
                        <tr>
                            <th style="width: 40%">Roll Number</th>
                            <td style="width: 60%">${student.rollNumber}</td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Join Year</th>
                            <td style="width: 60%">${student.joinYear}</td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Department Id</th>
                            <td style="width: 60%">${student.departmentId}</td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Caste</th>
                            <td style="width: 60%">${student.caste}</td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Family Income</th>
                            <td style="width: 60%">${student.familyIncome}</td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Gaurdian Name</th>
                            <td style="width: 60%">${student.gaurdianName}</td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Gaurdian Relation</th>
                            <td style="width: 60%">${student.gaurdianRelation}</td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Gaurdian Phone</th>
                            <td style="width: 60%">${student.gaurdianPhoneNumber}</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>