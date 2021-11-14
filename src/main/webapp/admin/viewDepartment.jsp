<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <div class="d-flex align-items-center">
        <h2 style="cursor : pointer;" onclick="location.href='/admin/departments'">Departments  >  </h2>
        <h3 class="ms-2">${department.departmentId}</h3>
    </div>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">
            
            <div class="d-flex ms-auto">
                <button class="btn btn-primary me-2"  onclick="location.href='/admin/departments/${department.departmentId}/edit'">Edit Department</button>
                <button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">Delete Department</button>

                <!-- Modal -->
                <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteModalLabel">Delete Department</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Are you sure want to delete ?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-bs-dismiss="modal">Cancel</button>
                        <button class="btn btn-danger" onclick="location.href='/admin/departments/${department.departmentId}/delete'">Delete</button>
                    </div>
                    </div>
                </div>
                </div>

            </div>

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

            <h4 class="text-center pt-2">HOD Details</h4>

            <button class="btn btn-primary ms-auto" onclick="location.href='/admin/hods/${department.departmentId}/add'">Add Entry
            </button>

            <div class="table-responsive mt-3 p-1">
            <table id="example" class="table table-hover">
                <thead>
                    <tr>
                        <th>Professor Id</th>
                        <th>Professor Name</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${hods}" var="hod">
                    <tr>
                        <td class="align-middle">${hod.professor.professorId}</td>
                        <td class="align-middle">${hod.professor.employee.user.firstName} ${hod.professor.employee.user.lastName}</td>
                        <td class="align-middle">${hod.startDate}</td>
                        <td class="align-middle">${hod.endDate}</td>
                        <td class="align-middle">
                        <div class="d-flex">
                            <button class="btn btn-primary me-2" onclick="location.href='/admin/hods/${department.departmentId}/${hod.professor.professorId}/${hod.startDate}/edit'">
                                Edit
                            </button>
                            <button class="btn btn-danger me-2" onclick="location.href='/admin/hods/${department.departmentId}/${hod.professor.professorId}/${hod.startDate}/delete'">
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