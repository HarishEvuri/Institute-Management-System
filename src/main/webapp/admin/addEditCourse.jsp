<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <h2>
        <c:choose>
            <c:when test="${not empty edit}">
                Edit Course
            </c:when>
            <c:otherwise>
                Add Course
            </c:otherwise>
        </c:choose>
    </h2>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">
            
            <form:form action="${submiturl}" method="post" modelAttribute="course">
            <div class="row mt-3">
                <table class="table table-borderless">
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Course Id</th>
                        <td style="width: 50%">
                            <c:choose>
                                <c:when test="${not empty edit}">
                                    <form:input type="text" path="courseId" class="form-control" required="true" disabled="true"></form:input>
                                    <form:errors path="courseId" style="color: red;"></form:errors>
                                </c:when>
                                <c:otherwise>
                                    <form:input type="text" path="courseId" class="form-control" required="true" placeholder="Course Id"></form:input>
                                    <form:errors path="courseId" style="color: red;"></form:errors>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Course Name</th>
                        <td style="width: 50%">
                            <form:input type="text" path="name" class="form-control" required="true" placeholder="Course Name"></form:input>
                            <form:errors path="name" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Credits</th>
                        <td style="width: 50%">
                            <form:input type="text" path="credits" class="form-control" required="true" placeholder="Credits"></form:input>
                            <form:errors path="credits" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Department Id</th>
                        <td style="width: 50%">
                            <form:select class="form-control" path="departmentId" required="true">
                                <c:forEach var="department" items="${departments}">
                                    <form:option value="${department.departmentId}">${department.departmentId}</form:option>
                                </c:forEach></td>
                            </form:select>
                            <form:errors path="departmentId" style="color: red;"></form:errors>
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