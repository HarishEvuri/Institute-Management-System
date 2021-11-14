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
                Edit Student
            </c:when>
            <c:otherwise>
                Add Student
            </c:otherwise>
        </c:choose>
    </h2>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">
            
            <form:form action="${submiturl}" method="post" modelAttribute="student">
            <div class="row mt-3">

                <div class="col-lg-6">
                    <table class="table table-borderless mb-0">
                        <tr>
                            <th style="width: 40%">Username</th>
                            <td style="width: 60%">
                            <c:choose>
                                <c:when test="${not empty edit}">
                                    <form:input type="text" path="user.username" class="form-control" required="true" disabled="true"></form:input>
                                    <form:errors path="user.username" style="color: red;"></form:errors>
                                </c:when>
                                <c:otherwise>
                                    <form:input type="text" path="user.username" class="form-control" required="true" placeholder="Username"></form:input>
                                    <form:errors path="user.username" style="color: red;"></form:errors>
                                </c:otherwise>
                            </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">First Name</th>
                            <td style="width: 60%">
                                <form:input type="text" path="user.firstName" class="form-control" required="true" placeholder="First Name"></form:input>
                                <form:errors path="user.firstName" style="color: red;"></form:errors>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Last Name</th>
                            <td style="width: 60%">
                                <form:input type="text" path="user.lastName" class="form-control" required="true" placeholder="Last Name"></form:input>
                                <form:errors path="user.lastName" style="color: red;"></form:errors>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Email Address</th>
                            <td style="width: 60%">
                                <form:input type="email" path="user.emailAddress" class="form-control" required="true" placeholder="Email"></form:input>
                                <form:errors path="user.emailAddress" style="color: red;"></form:errors>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Date of Birth</th>
                            <td style="width: 60%">
                                <form:input type="date" path="user.dateOfBirth" class="form-control" required="true"></form:input>
                                <form:errors path="user.dateOfBirth" style="color: red;"></form:errors>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Gender</th>
                            <td style="width: 60%">
                                <form:select class="form-control" path="user.gender" required="true">
                                    <form:option value="Male">Male</form:option>
                                    <form:option value="Female">Female</form:option>
                                    <form:option value="Not Specified">Not Specified</form:option>
                                </form:select>
                                <form:errors path="user.gender" style="color: red;"></form:errors>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Address</th>
                            <td style="width: 60%">
                                <form:input type="text" path="user.address" class="form-control" required="true" placeholder="Address"></form:input>
                                <form:errors path="user.address" style="color: red;"></form:errors>
                            </td>
                        </tr>
                    </table>
                </div>

                <div class="col-lg-6">
                    <table class="table table-borderless">
                        <tr>
                            <th style="width: 40%">Roll Number</th>
                            <td style="width: 60%">
                                <c:choose>
                                <c:when test="${not empty edit}">
                                    <form:input type="text" path="rollNumber" class="form-control" required="true" disabled="true"></form:input>
                                    <form:errors path="rollNumber" style="color: red;"></form:errors>
                                </c:when>
                                <c:otherwise>
                                    <form:input type="text" path="rollNumber" class="form-control" required="true" placeholder="Roll Number"></form:input>
                                    <form:errors path="rollNumber" style="color: red;"></form:errors>
                                </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Join Year</th>
                            <td style="width: 60%">
                                <form:input type="number" path="joinYear" class="form-control" min="2001" required="true" placeholder="Join Year"></form:input>
                                <form:errors path="joinYear" style="color: red;"></form:errors>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Department Id</th>
                            <td style="width: 60%">
                                <form:select class="form-control" path="departmentId" required="true">
                                    <c:forEach var="department" items="${departments}">
                                        <form:option value="${department.departmentId}">${department.departmentId}</form:option>
                                    </c:forEach></td>
                                </form:select>
                                <form:errors path="departmentId" style="color: red;"></form:errors>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Caste</th>
                            <td style="width: 60%">
                                <form:select path="caste" class="form-control" required="true">
                                    <form:option value="OC">OC</form:option>
                                    <form:option value="OBC">OBC</form:option>
                                    <form:option value="SC">SC</form:option>
                                    <form:option value="ST">ST</form:option>
                                </form:select>
                                <form:errors path="caste" style="color: red;"></form:errors>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Family Income</th>
                            <td style="width: 60%">
                                <form:select class="form-control" path="familyIncome" required="true">
                                    <form:option value="<1 lac">${"<1 lac"}</form:option>
                                    <form:option value="1 lac - 5 lacs">1 lac - 5 lacs</form:option>
                                    <form:option value=">5 lacs">${">5 lacs"}</form:option>
                                </form:select>
                                <form:errors path="familyIncome" style="color: red;"></form:errors>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Gaurdian Name</th>
                            <td style="width: 60%">
                                <form:input type="text" path="gaurdianName" class="form-control" required="true" placeholder="Gaurdian Name"></form:input>
                                <form:errors path="gaurdianName" style="color: red;"></form:errors>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Gaurdian Relation</th>
                            <td style="width: 60%">
                                <form:select class="form-control" path="gaurdianRelation" required="true">
                                    <form:option value="Father">Father</form:option>
                                    <form:option value="Mother">Mother</form:option>
                                    <form:option value="Brother">Brother</form:option>
                                    <form:option value="Sister">Sister</form:option>
                                    <form:option value="Other">Other</form:option>
                                </form:select>
                                <form:errors path="gaurdianRelation" style="color: red;"></form:errors>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 40%">Gaurdian Phone</th>
                            <td style="width: 60%">
                                <form:input type="text" path="gaurdianPhoneNumber" class="form-control" required="true" placeholder="Gaurdian Phone"></form:input>
                                <form:errors path="gaurdianPhoneNumber" style="color: red;"></form:errors>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="d-flex justify-content-center">
                <button class="btn btn-success" type="submit">Submit</button>
            </div>
            
            </form:form>
        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>