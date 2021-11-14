<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <h2>
        Reset Password
    </h2>
    <div class="row justify-content-center align-items-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">

            <form action="${submiturl}" method="post">
            <div class="row mt-3">

                <c:if test="${not empty error}">
                    <table class="table table-borderless">
                        <tr>
                            <td style="width: 40%"></td>
                            <td style="width: 35% text-center">
                                <div class="alert alert-danger">${error}</div>
                            </td>
                            <td style="width: 15%"></td>
                        </tr>
                    </table>
                </c:if>

                <table class="table table-borderless">
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 30%" class="text-end">Password</th>
                        <td style="widht: 5%"></td>
                        <td style="width: 35%">
                            <input type="password" name="password" class="form-control" required="true" placeholder="Password">
                        </td>
                        <td style="width: 15%"></td>
                    </tr>
                    <tr>
                        <td style="width: 15%"></td>
                        <th style="width: 30%" class="text-end">Confirm Password</th>
                        <td style="widht: 5%"></td>
                        <td style="width: 35%">
                            <input type="password" name="confirmPassword" class="form-control" required="true" placeholder="confirm Password">
                        </td>
                        <td style="width: 15%"></td>
                    </tr>
                </table>
            </div>

            <div class="d-flex justify-content-center">
                <button class="btn btn-success" type="submit">Submit</button>
            </div>
            
            </form>

        </div>
    </div>

</div>

<%@ include file="/template/footer.jsp" %>