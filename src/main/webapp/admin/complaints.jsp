<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <h2>Complaints</h2>

    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-11 p-4 shadow">
        
        <div class="table-responsive mt-3 p-1">
            <table id="example" class="table table-hover">
                <thead>
                    <tr>
                        <th>Complaint Id</th>
                        <th>Roll Number</th>
                        <th>Name</th>
                        <th>Timestamp</th>
                        <th>Is Responded</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${complaints}" var="complaint">
                    <tr>
                        <td class="align-middle">${complaint.complaintId}</td>
                        <td class="align-middle">${complaint.student.rollNumber}</td>
                        <td class="align-middle">${complaint.student.user.firstName} ${complaint.student.user.lastName}</td>
                        <td class="align-middle">${complaint.timestamp}</td>
                        <td class="align-middle">
                            <c:choose>
                                <c:when test="${not empty complaint.response}">Yes</c:when>
                                <c:otherwise>No</c:otherwise>
                            </c:choose>
                        </td>
                        <td class="align-middle">
                            <button class="btn btn-success me-2" onclick="location.href='/admin/complaints/${complaint.complaintId}/respond'">
                                Respond
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>