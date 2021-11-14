<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <div class="d-flex align-items-center">
        <h2 style="cursor : pointer;" onclick="location.href='/professor/departments'">Departments  >  </h2>
        <h3 class="ms-2">${department.departmentId}</h3>
    </div>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">

            <table class="table table-borderless mt-3">
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">DepartmentId</th>
                    <td style="width: 50%">${department.departmentId}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Department Name</th>
                    <td style="width: 50%">${department.departmentName}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Address</th>
                    <td style="width: 50%">${department.address}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Current HOD</th>
                    <td style="width: 50%">${currentHOD.professor.employee.user.firstName} ${currentHOD.professor.employee.user.lastName}</td>
                </tr>
            </table>

            <h4 class="text-center pt-2">Professors</h4>

            <div class="table-responsive mt-3 p-1">
            <table id="example" class="table table-hover">
                <thead>
                    <tr>
                        <th>Professor Id</th>
                        <th>Professor Name</th>
                        <th>Email Address</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${professors}" var="professor">
                    <tr>
                        <td class="align-middle">${professor.professorId}</td>
                        <td class="align-middle">${professor.employee.user.firstName} ${professor.employee.user.lastName}</td>
                        <td class="align-middle">${professor.employee.user.emailAddress}</td>
                        <td class="align-middle">${professor.employee.joinDate}</td>
                        <td class="align-middle">${professor.employee.endDate}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </div>            
        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>