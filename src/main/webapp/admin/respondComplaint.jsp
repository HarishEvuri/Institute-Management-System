<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <div class="d-flex align-items-center">
        <h2 style="cursor : pointer;" onclick="location.href='/admin/complaints'">Complaints  >  </h2>
        <h3 class="ms-2">${complaint.complaintId}</h3>
    </div>

    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">
            
            <form:form action="${submiturl}" method="post" modelAttribute="complaint">
            <div class="row mt-3">
                <table class="table table-borderless">
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Complaint Id</th>
                        <td style="width: 50%">
                            <form:input type="text" path="complaintId" class="form-control" required="true" disabled="true"></form:input>
                            <form:errors path="complaintId" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Roll Number</th>
                        <td style="width: 50%">
                            <form:input type="text" path="student.rollNumber" class="form-control" required="true" disabled="true"></form:input>
                            <form:errors path="student.rollNumber" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Subject</th>
                        <td style="width: 50%">
                            <form:input type="text" path="subject" class="form-control" required="true" disabled="true"></form:input>
                            <form:errors path="subject" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Year</th>
                        <td style="width: 50%">
                            <form:textarea path="description" class="form-control" required="true" disabled="true"></form:textarea>
                            <form:errors path="description" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Response</th>
                        <td style="width: 50%">
                            <form:textarea path="response" class="form-control" required="true" placeholder="Response"></form:textarea>
                            <form:errors path="response" style="color: red;"></form:errors>
                        </td>
                    </tr>
                </table>
            </div>

            <div class="d-flex justify-content-center">
                <button class="btn btn-success" type="submit">Submit</button>
            </div>
            
            </form:form>
        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>