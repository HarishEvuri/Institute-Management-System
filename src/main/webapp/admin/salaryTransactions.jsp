<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <h2>Salary Transactions</h2>

    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-11 p-4 shadow">
        
        <button class="btn btn-primary ms-auto" onclick="location.href='/admin/salary-transactions/add'">Add Transaction
        </button>
        
        <div class="table-responsive mt-3 p-1">
            <table id="example" class="table table-hover">
                <thead>
                    <tr>
                        <th>Transaction Id</th>
                        <th>Employee Id</th>
                        <th>Name</th>
                        <th>Transaction Date</th>
                        <th>Transaction Time</th>
                        <th>Month</th>
                        <th>Year</th>
                        <th>Amount</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${salaryPayments}" var="salaryPayment">
                    <tr>
                        <td class="align-middle">${salaryPayment.transactionId}</td>
                        <td class="align-middle">${salaryPayment.employee.employeeId}</td>
                        <td class="align-middle">${salaryPayment.employee.user.firstName} ${salaryPayment.employee.user.lastName}</td>
                        <td class="align-middle">
                            <fmt:formatDate pattern="dd-MM-yyyy" value="${salaryPayment.transactionDate}" />
                        </td>
                        <td class="align-middle">${salaryPayment.transactionTime}</td>
                        <td class="align-middle">${salaryPayment.month}</td>
                        <td class="align-middle">${salaryPayment.year}</td>
                        <td class="align-middle">${salaryPayment.amount}</td>
                        <td class="align-middle">
                        <div class="d-flex">
                            <button class="btn btn-primary me-2" onclick="location.href='/admin/salary-transactions/${salaryPayment.transactionId}/edit'">
                                Edit
                            </button>
                            <button class="btn btn-danger me-2" onclick="location.href='/admin/fee-transactions/${feePayment.transactionId}/delete'">
                                Delete
                            </button>
                        </div>
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