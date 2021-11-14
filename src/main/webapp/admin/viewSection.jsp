<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <div class="d-flex align-items-center">
        <h2 style="cursor : pointer;" onclick="location.href='/admin/sections'">Sections  >  </h2>
        <h3 class="ms-2">${section.course.courseId} ${section.sectionId}</h3>
    </div>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">
            
            <div class="d-flex ms-auto">
                <button class="btn btn-primary me-2"  onclick="location.href='/admin/sections/${section.course.courseId}/${section.sectionId}/edit'">Edit Section</button>
                <button class="btn btn-success me-2"  onclick="location.href='/admin/sections/${section.course.courseId}/${section.sectionId}/timings'">Manage Timings</button>
                <button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">Delete Section</button>

                <!-- Modal -->
                <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteModalLabel">Delete Section</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Are you sure want to delete ?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-bs-dismiss="modal">Cancel</button>
                        <button class="btn btn-danger" onclick="location.href='/admin/sections/${section.course.courseId}/${section.sectionId}/delete'">Delete</button>
                    </div>
                    </div>
                </div>
                </div>
            </div>

            <table class="table table-borderless mt-3">
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Course Id</th>
                    <td style="width: 50%">${section.course.courseId}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Section Id</th>
                    <td style="width: 50%">${section.sectionId}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Course Name</th>
                    <td style="width: 50%">${section.course.name}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Credits</th>
                    <td style="width: 50%">${section.course.credits}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Professor Id</th>
                    <td style="width: 50%">${section.professor.professorId}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Professor Name</th>
                    <td style="width: 50%">${section.professor.employee.user.firstName} ${section.professor.employee.user.lastName}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Locked Status</th>
                    <td style="width: 50%">${section.isLocked}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Year</th>
                    <td style="width: 50%">${section.year}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Semester</th>
                    <td style="width: 50%">${section.semester}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Room Number</th>
                    <td style="width: 50%">${section.roomNumber}</td>
                </tr>
                <tr>
                    <td style="width: 15%"></td>
                    <th style="width: 35%;">Section Timings</th>
                    <td style="width: 50%">
                        <c:forEach var="sectionTiming" items="${sectionTimings}">
                            <div class="pt-1">${sectionTiming.day}   ${sectionTiming.slot}</div>
                        </c:forEach></td>
                    </td>
                </tr>
            </table>

            <h4 class="text-center pt-2">Enrollments</h4>
            
            <form:form action="${submiturl}" method="post" modelAttribute="enrollment">
            <div class="row mt-3">
                <table class="table table-borderless">
                    <tr>
                        <th style="widht: 15%"></th>
                        <th style="width: 30%;">Student Roll Number</th>
                        <td style="width: 30%">  
                            <form:input type="text" path="student.rollNumber" class="form-control" required="true" placeholder="Roll Number"></form:input>
                            <form:errors path="student.rollNumber" style="color: red;"></form:errors>        
                        </td>
                        <td style="width: 25%">
                            <button class="btn btn-success" type="submit">Add Enrollment</button>
                        </td>
                    </tr>
                </table>
            </div>
            </form:form>

            <div class="table-responsive mt-3 p-1">
            <table id="example" class="table table-hover">
                <thead>
                    <tr>
                        <th>Roll Number</th>
                        <th>Name</th>
                        <th>Department</th>
                        <th>Email</th>
                        <th>Grade</th>
                        <th>Attendance</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${enrollments}" var="enrollment">
                    <tr>
                        <td class="align-middle">${enrollment.student.rollNumber}</td>
                        <td class="align-middle">${enrollment.student.user.firstName} ${enrollment.student.user.lastName}</td>
                        <td class="align-middle">${enrollment.student.departmentId}</td>
                        <td class="align-middle">${enrollment.student.user.emailAddress}</td>
                        <td class="align-middle">${enrollment.grade}</td>
                        <td class="align-middle">${enrollment.attendance}</td>
                        <td class="align-middle">
                        <div class="d-flex">
                            <button class="btn btn-primary me-2" onclick="location.href='/admin/enrollments/${section.course.courseId}/${section.sectionId}/${enrollment.student.rollNumber}/edit'">
                                Edit
                            </button>
                            <button class="btn btn-danger me-2" onclick="location.href='/admin/enrollments/${section.course.courseId}/${section.sectionId}/${enrollment.student.rollNumber}/delete'">
                                Unenroll
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