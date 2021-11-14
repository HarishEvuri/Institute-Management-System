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
                Edit Salary Transaction
            </c:when>
            <c:otherwise>
                Add Salary Transaction
            </c:otherwise>
        </c:choose>
    </h2>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">
            
            <form:form action="${submiturl}" method="post" modelAttribute="salaryPayment">
            <div class="row mt-3">
                <table class="table table-borderless">
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Transaction Id</th>
                        <td style="width: 50%">
                            <c:choose>
                                <c:when test="${not empty edit}">
                                    <form:input type="text" path="transactionId" class="form-control" required="true" disabled="true"></form:input>
                                    <form:errors path="transactionId" style="color: red;"></form:errors>
                                </c:when>
                                <c:otherwise>
                                    <form:input type="text" path="transactionId" class="form-control" required="true" placeholder="Transaction Id"></form:input>
                                    <form:errors path="transactionId" style="color: red;"></form:errors>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Employee Id</th>
                        <td style="width: 50%">
                            <form:input type="text" path="employee.employeeId" class="form-control" required="true" placeholder="Employee Id"></form:input>
                            <form:errors path="employee.employeeId" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Transaction Date</th>
                        <td style="width: 50%">
                            <form:input type="date" path="transactionDate" class="form-control" required="true"></form:input>
                            <form:errors path="transactionDate" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Transaction Time</th>
                        <td style="width: 50%">
                            <form:input type="time" path="transactionTime" class="form-control" required="true"></form:input>
                            <form:errors path="transactionTime" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">month</th>
                        <td style="width: 50%">
                            <form:select class="form-control" path="month" required="true">
                                <form:option value="January">January</form:option>
                                <form:option value="February">February</form:option>
                                <form:option value="March">March</form:option>
                                <form:option value="April">April</form:option>
                                <form:option value="May">May</form:option>
                                <form:option value="June">June</form:option>
                                <form:option value="July">July</form:option>
                                <form:option value="August">August</form:option>
                                <form:option value="September">September</form:option>
                                <form:option value="October">October</form:option>
                                <form:option value="November">November</form:option>
                                <form:option value="December">December</form:option>
                            </form:select>
                            <form:errors path="month" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Year</th>
                        <td style="width: 50%">
                            <form:input type="number" path="year" class="form-control" required="true" placeholder="Year"></form:input>
                            <form:errors path="year" style="color: red;"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 35%;">Amount</th>
                        <td style="width: 50%">
                            <form:input type="number" path="amount" class="form-control" required="true" placeholder="Amount"></form:input>
                            <form:errors path="amount" style="color: red;"></form:errors>
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