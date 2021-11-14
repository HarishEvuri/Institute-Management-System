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
                Edit Department
            </c:when>
            <c:otherwise>
                Add Department
            </c:otherwise>
        </c:choose>
    </h2>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">
            
            <form:form action="${submiturl}" method="post" modelAttribute="department">
            <div class="row mt-3">
                <table class="table table-borderless">
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Department Id</th>
                        <td style="width: 50%">
                            <c:choose>
                                <c:when test="${not empty edit}">
                                    <form:input type="text" path="departmentId" class="form-control" required="true" disabled="true"></form:input>
                                    <form:errors path="departmentId" style="color: red;"></form:errors>
                                </c:when>
                                <c:otherwise>
                                    <form:input type="text" path="departmentId" class="form-control" required="true" placeholder="Department Id"></form:input>
                                    <form:errors path="departmentId" style="color: red;"></form:errors>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Department Name</th>
                        <td style="width: 50%">
                            <form:input type="text" path="departmentName" class="form-control" required="true" placeholder="Department Name"></form:input>
                            <form:errors path="departmentName" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Address</th>
                        <td style="width: 50%">
                            <form:input type="text" path="address" class="form-control" required="true" placeholder="Address"></form:input>
                            <form:errors path="address" style="color: red;"></form:errors>
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