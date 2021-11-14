<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <div class="d-flex align-items-center">
        <h2 style="cursor : pointer;" onclick="location.href='/admin/courses'">Courses  >  </h2>
        <h3 class="ms-2">${course.courseId}</h3>
    </div>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">
            
            <div class="d-flex ms-auto">
                <button class="btn btn-primary me-2"  onclick="location.href='/admin/courses/${course.courseId}/edit'">Edit Course</button>
                <button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">Delete Course</button>

                <!-- Modal -->
                <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteModalLabel">Delete Course</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Are you sure want to delete ?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-bs-dismiss="modal">Cancel</button>
                        <button class="btn btn-danger" onclick="location.href='/admin/courses/${course.courseId}/delete'">Delete</button>
                    </div>
                    </div>
                </div>
                </div>

            </div>

            <table class="table table-borderless mt-3">
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Course Id</th>
                    <td style="width: 50%">${course.courseId}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Course Name</th>
                    <td style="width: 50%">${course.name}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Credits</th>
                    <td style="width: 50%">${course.credits}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Department Id</th>
                    <td style="width: 50%">${course.departmentId}</td>
                </tr>
            </table>
            
        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>