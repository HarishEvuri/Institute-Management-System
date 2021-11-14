<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <h2>Staff</h2>

    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-11 p-4 shadow">
        
        <div class="table-responsive mt-3 p-1">
            <table id="example" class="table table-hover">
                <thead>
                    <tr>
                        <th>Staff Id</th>
                        <th>Name</th>
                        <th>Designation</th>
                        <th>Employee Id</th>
                        <th>Join Date</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Date of Birth</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${staffs}" var="staff">
                    <tr>
                        <td class="align-middle">${staff.staffId}</td>
                        <td class="align-middle">${staff.employee.user.firstName} ${staff.employee.user.lastName}</td>
                        <td class="align-middle">${staff.designation}</td>
                        <td class="align-middle">${staff.employee.employeeId}</td>
                        <td class="align-middle">
                            <fmt:formatDate pattern="dd-MM-yyyy" value="${staff.employee.joinDate}" />
                        </td>
                        <td class="align-middle">${staff.employee.user.username}</td>
                        <td class="align-middle">${staff.employee.user.emailAddress}</td>
                        <td class="align-middle">
                            <fmt:formatDate pattern="dd-MM-yyyy" value="${staff.employee.user.dateOfBirth}" />
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