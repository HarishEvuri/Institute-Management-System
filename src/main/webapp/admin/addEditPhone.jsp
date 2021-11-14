<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <h2>Mange Phone</h2>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">

            <table class="table table-borderless my-3">
                <c:forEach items="${userPhoneNumbers}" var="userPhoneNumber">
                    <tr>
                        <td style="width: 40%"></td>
                        <td style="width: 20%">${userPhoneNumber.phoneNumber}</td>
                        <td style="width: 40%">
                            <button class="btn btn-danger" onclick="location.href='/admin/user/${userPhoneNumber.username}/${userPhoneNumber.phoneNumber}/delete'">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            
            <form:form action="${submiturl}" method="post" modelAttribute="phoneNumber">
            <div class="row mt-3">
                <table class="table table-borderless">
                    <tr>
                        <td style="width: 40%;"></th>
                        <td style="width: 20%">
                            <form:input type="text" path="phoneNumber" class="form-control" required="true" placeholder="Phone Number"></form:input>
                            <form:errors path="phoneNumber" style="color: red;"></form:errors>
                        </td>
                        <td style="width: 40%">
                            <button class="btn btn-success" type="submit">Add</button>
                        </td>
                    </tr>
                </table>
            </div>
            </form:form>

            <div class="d-flex justify-content-center">
                <button class="btn btn-success" onclick="location.href='${finishurl}'">Finish</button>
            </div>

        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>