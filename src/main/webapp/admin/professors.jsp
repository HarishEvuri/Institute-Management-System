<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <h2>Professors</h2>

    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-11 p-4 shadow">
        
        <button class="btn btn-primary ms-auto"  onclick="location.href='/admin/professors/add'">Add Professor
        </button>
        
        <div class="table-responsive mt-3 p-1">
            <table id="example" class="table table-hover">
                <thead>
                    <tr>
                        <th>Professor Id</th>
                        <th>Name</th>
                        <th>Department</th>
                        <th>Employee Id</th>
                        <th>Join Date</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Date of Birth</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${professors}" var="professor">
                    <tr>
                        <td class="align-middle">${professor.professorId}</td>
                        <td class="align-middle">${professor.employee.user.firstName} ${professor.employee.user.lastName}</td>
                        <td class="align-middle">${professor.departmentId}</td>
                        <td class="align-middle">${professor.employee.employeeId}</td>
                        <td class="align-middle">
                            <fmt:formatDate pattern="dd-MM-yyyy" value="${professor.employee.joinDate}" />
                        </td>
                        <td class="align-middle">${professor.employee.user.username}</td>
                        <td class="align-middle">${professor.employee.user.emailAddress}</td>
                        <td class="align-middle">
                            <fmt:formatDate pattern="dd-MM-yyyy" value="${professor.employee.user.dateOfBirth}" />
                        </td>
                        <td class="align-middle">
                            <button class="btn btn-success me-2" onclick="location.href='/admin/professors/${professor.professorId}'">
                                Manage
                            </button>
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