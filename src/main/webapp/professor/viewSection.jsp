<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <div class="d-flex align-items-center">
        <h2 style="cursor : pointer;" onclick="location.href='/professor/sections'">Sections  >  </h2>
        <h3 class="ms-2">${section.course.courseId} ${section.sectionId}</h3>
    </div>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">

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
                        <c:if test = "${!section.isLocked}">
                            <th>Action</th>
                        </c:if>
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
                        <c:if test = "${!section.isLocked}">
                        <td class="align-middle">
                            <button class="btn btn-primary me-2" onclick="location.href='/professor/enrollments/${section.course.courseId}/${section.sectionId}/${enrollment.student.rollNumber}/edit'">
                                Edit
                            </button>
                        </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </div>
            
        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>