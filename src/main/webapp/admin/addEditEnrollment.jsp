<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <h2>
        Edit Enrollment
    </h2>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">
            
            <form:form action="${submiturl}" method="post" modelAttribute="enrollment">
            <div class="row mt-3">
                <table class="table table-borderless">
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Course Id</th>
                        <td style="width: 50%">
                            <input type="text" value="${courseId}" class="form-control" disabled="true">
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Section Id</th>
                        <td style="width: 50%">
                            <input type="text" value="${sectionId}" class="form-control" disabled="true">
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Roll Number</th>
                        <td style="width: 50%">
                            <input type="text" value="${rollNumber}" class="form-control" disabled="true">
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Grade</th>
                        <td style="width: 50%">
                            <form:select class="form-control" path="grade" required="true">
                                <form:option value="A">A</form:option>
                                <form:option value="A-">A-</form:option>
                                <form:option value="B">B</form:option>
                                <form:option value="B-">B-</form:option>
                                <form:option value="C">C</form:option>
                                <form:option value="C-">C-</form:option>
                                <form:option value="F">F</form:option>
                                <form:option value="Not Graded">Not Graded</form:option>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Attendance</th>
                        <td style="width: 50%">
                            <form:input type="number" path="attendance" class="form-control" required="true" placeholder="Attendance"></form:input>
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