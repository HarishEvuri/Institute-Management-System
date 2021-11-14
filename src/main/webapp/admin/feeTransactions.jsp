<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <h2>Fee Transactions</h2>

    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-11 p-4 shadow">
        
        <button class="btn btn-primary ms-auto" onclick="location.href='/admin/fee-transactions/add'">Add Transaction
        </button>
        
        <div class="table-responsive mt-3 p-1">
            <table id="example" class="table table-hover">
                <thead>
                    <tr>
                        <th>Transaction Id</th>
                        <th>Roll Number</th>
                        <th>Name</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Semester</th>
                        <th>Year</th>
                        <th>Amount</th>
                        <th>Payment Mode</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${feePayments}" var="feePayment">
                    <tr>
                        <td class="align-middle">${feePayment.transactionId}</td>
                        <td class="align-middle">${feePayment.student.rollNumber}</td>
                        <td class="align-middle">${feePayment.student.user.firstName} ${feePayment.student.user.lastName}</td>
                        <td class="align-middle">
                            <fmt:formatDate pattern="dd-MM-yyyy" value="${feePayment.transactionDate}" />
                        </td>
                        <td class="align-middle">${feePayment.transactionTime}</td>
                        <td class="align-middle">${feePayment.semester}</td>
                        <td class="align-middle">${feePayment.year}</td>
                        <td class="align-middle">${feePayment.amount}</td>
                        <td class="align-middle">${feePayment.modeOfPayment}</td>
                        <td class="align-middle">
                        <div class="d-flex">
                            <button class="btn btn-primary me-2" onclick="location.href='/admin/fee-transactions/${feePayment.transactionId}/edit'">
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