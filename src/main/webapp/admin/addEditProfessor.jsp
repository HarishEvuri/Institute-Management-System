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
                Edit Professor
            </c:when>
            <c:otherwise>
                Add Professor
            </c:otherwise>
        </c:choose>
    </h2>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">

            <form:form action="${submiturl}" method="post" modelAttribute="professor">            
            <div class="row mt-3">

                <div class="col-lg-6">
                    <table class="table table-borderless mb-0">
                        <tr>
                            <th style="width: 40%">Username</th>
                            <td style="width: 60%">
                                <c:choose>
                                <c:when test="${not empty edit}">
                                    <form:input type="text" path="employee.user.username" class="form-control" required="true" disabled="true"></form:input>
                                    <form:errors path="employee.user.username" style="color: red;"></form:errors>
                                </c:when>
                                <c:otherwise>
                                    <form:input type="text" path="employee.user.username" class="form-control" required="true" placeholder="Username"></form:input>
                                    <form:errors path="employee.user.username" style="color: red;"></form:errors>
                                </c:otherwise>
                            </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">First Name</th>
                            <td style="width: 60%">
                                <form:input type="text" path="employee.user.firstName" class="form-control" required="true" placeholder="First Name"></form:input>
                                <form:errors path="employee.user.firstName" style="color: red;"></form:errors>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Last Name</th>
                            <td style="width: 60%">
                                <form:input type="text" path="employee.user.lastName" class="form-control" required="true" placeholder="Last Name"></form:input>
                                <form:errors path="employee.user.lastName" style="color: red;"></form:errors>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Email Address</th>
                            <td style="width: 60%">
                                <form:input type="email" path="employee.user.emailAddress" class="form-control" required="true" placeholder="Email Address"></form:input>
                                <form:errors path="employee.user.emailAddress" style="color: red;"></form:errors>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Date of Birth</th>
                            <td style="width: 60%">
                                <form:input type="date" path="employee.user.dateOfBirth" class="form-control" required="true" placeholder="Date of Birth"></form:input>
                                <form:errors path="employee.user.dateOfBirth" style="color: red;"></form:errors>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Gender</th>
                            <td style="width: 60%">
                                <form:select class="form-control" path="employee.user.gender" required="true">
                                    <form:option value="Male">Male</form:option>
                                    <form:option value="Female">Female</form:option>
                                    <form:option value="Not Specified">Not Specified</form:option>
                                </form:select>
                                <form:errors path="employee.user.gender" style="color: red;"></form:errors>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Address</th>
                            <td style="width: 60%">
                                <form:input type="text" path="employee.user.address" class="form-control" required="true" placeholder="Address"></form:input>
                                <form:errors path="employee.user.address" style="color: red;"></form:errors>
                            </td>
                        </tr>
                    </table>
                </div>

                <div class="col-lg-6">
                    <table class="table table-borderless">
                        <tr>
                            <th style="width: 40%">Professor Id</th>
                            <td style="width: 60%">
                                <c:choose>
                                <c:when test="${not empty edit}">
                                    <form:input type="text" path="professorId" class="form-control" required="true" disabled="true"></form:input>
                                    <form:errors path="professorId" style="color: red;"></form:errors>
                                </c:when>
                                <c:otherwise>
                                    <form:input type="text" path="professorId" class="form-control" required="true" placeholder="Professor Id"></form:input>
                                    <form:errors path="professorId" style="color: red;"></form:errors>
                                </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Department Id</th>
                            <td style="width: 60%">
                                <form:select class="form-control" path="departmentId" required="true">
                                    <c:forEach var="department" items="${departments}">
                                        <form:option value="${department.departmentId}">${department.departmentId}</form:option>
                                    </c:forEach></td>
                                </form:select>
                                <form:errors path="departmentId" style="color: red;"></form:errors>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Qualification</th>
                            <td style="width: 60%">
                                <form:input type="text" path="qualification" class="form-control" required="true" placeholder="Qualification"></form:input>
                                <form:errors path="qualification" style="color: red;"></form:errors>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Employee Id</th>
                            <td style="width: 60%">
                                <c:choose>
                                <c:when test="${not empty edit}">
                                    <form:input type="text" path="employee.employeeId" class="form-control" required="true" disabled="true"></form:input>
                                    <form:errors path="employee.employeeId" style="color: red;"></form:errors>
                                </c:when>
                                <c:otherwise>
                                    <form:input type="text" path="employee.employeeId" class="form-control" required="true" placeholder="Employee Id"></form:input>
                                    <form:errors path="employee.employeeId" style="color: red;"></form:errors>
                                </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Join Date</th>
                            <td style="width: 60%">
                                <form:input type="date" path="employee.joinDate" class="form-control" required="true"></form:input>
                                <form:errors path="employee.joinDate" style="color: red;"></form:errors>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">End Date</th>
                            <td style="width: 60%">
                                <form:input type="date" path="employee.endDate" class="form-control"></form:input>
                                <form:errors path="employee.endDate" style="color: red;"></form:errors>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Account Number</th>
                            <td style="width: 60%">
                                <form:input type="text" path="employee.accountNumber" class="form-control" required="true" placeholder="Account Number"></form:input>
                                <form:errors path="employee.accountNumber" style="color: red;"></form:errors>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Bank IFSC</th>
                            <td style="width: 60%">
                                <form:input type="text" path="employee.bank_IFSC_code" class="form-control" required="true" placeholder="Bank IFSC"></form:input>
                                <form:errors path="employee.bank_IFSC_code" style="color: red;"></form:errors>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Pan Number</th>
                            <td style="width: 60%">
                                <form:input type="text" path="employee.panNumber" class="form-control" required="true" placeholder="Pan Number"></form:input>
                                <form:errors path="employee.panNumber" style="color: red;"></form:errors>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="d-flex justify-content-center">
                <button class="btn btn-success" type="submit">Submit</button>
            </div>
    
            </form:form>
        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>