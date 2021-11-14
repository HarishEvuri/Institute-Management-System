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
                Edit Section
            </c:when>
            <c:otherwise>
                Add Section
            </c:otherwise>
        </c:choose>
    </h2>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">
            
            <form:form action="${submiturl}" method="post" modelAttribute="section">
            <div class="row mt-3">
                <table class="table table-borderless">
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Course Id</th>
                        <td style="width: 50%">
                            <c:choose>
                                <c:when test="${not empty edit}">
                                    <form:input type="text" path="course.courseId" class="form-control" required="true" disabled="true"></form:input>
                                    <form:errors path="course.courseId" style="color: red;"></form:errors>
                                </c:when>
                                <c:otherwise>
                                    <form:input type="text" path="course.courseId" class="form-control" required="true" placeholder="Course Id"></form:input>
                                    <form:errors path="course.courseId" style="color: red;"></form:errors>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Section Id</th>
                        <td style="width: 50%">
                            <c:choose>
                                <c:when test="${not empty edit}">
                                    <form:input type="text" path="sectionId" class="form-control" required="true" disabled="true"></form:input>
                                    <form:errors path="sectionId" style="color: red;"></form:errors>
                                </c:when>
                                <c:otherwise>
                                    <form:input type="text" path="sectionId" class="form-control" required="true" placeholder="Section Id"></form:input>
                                    <form:errors path="sectionId" style="color: red;"></form:errors>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Semester</th>
                        <td style="width: 50%">
                            <form:select class="form-control" path="semester" required="true">
                                <form:option value="even">Even</form:option>
                                <form:option value="odd">Odd</form:option>
                            </form:select>
                            <form:errors path="semester" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Year</th>
                        <td style="width: 50%">
                            <form:input type="number" path="year" class="form-control" min="2001" required="true" placeholder="Year"></form:input>
                            <form:errors path="year" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Room Number</th>
                        <td style="width: 50%">
                            <form:input type="text" path="roomNumber" class="form-control" required="true" placeholder="Room Number"></form:input>
                            <form:errors path="roomNumber" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Professor Id</th>
                        <td style="width: 50%">
                            <form:input type="text" path="professor.professorId" class="form-control" required="true" placeholder="Professor Id"></form:input>
                            <form:errors path="professor.professorId" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Locked Status</th>
                        <td style="width: 50%">
                            <form:select class="form-control" path="isLocked" required="true">
                                <form:option value="${false}">Open</form:option>
                                <form:option value="${true}">Locked</form:option>
                            </form:select>
                            <form:errors path="isLocked" style="color: red;"></form:errors>
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