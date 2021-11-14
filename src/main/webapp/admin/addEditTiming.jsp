<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="/template/header.jsp" %>

<div class="container my-4 px-3">
    <h2>Mange Timings</h2>
    <div class="row justify-content-center">
        <div class="card mt-4 col-lg-10 py-4 shadow" style="padding-left: 5%; padding-right: 5%">

            <table class="table table-borderless my-3">
                <c:forEach items="${sectionTimings}" var="st">
                    <tr>
                        <td style="width: 30%"></td>
                        <td style="width: 20%">${st.day}</td>
                        <td style="width: 20%">${st.slot}</td>
                        <td style="width: 30%">
                            <button class="btn btn-danger" onclick="location.href='/admin/sections/${st.courseId}/${st.sectionId}/${st.day}/${st.slot}/delete'">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            
            <form:form action="${submiturl}" method="post" modelAttribute="sectionTiming">
            <div class="row mt-3">
                <table class="table table-borderless">
                    <tr>
                        <td style="width: 30%;"></th>
                        <td style="width: 20%">
                            <form:select class="form-control" path="day" required="true">
                                <form:option value="MON">MON</form:option>
                                <form:option value="TUE">TUE</form:option>
                                <form:option value="WED">WED</form:option>
                                <form:option value="THU">THU</form:option>
                                <form:option value="FRI">FRI</form:option>
                            </form:select>
                        </td>
                        <td style="width: 20%">
                            <form:select class="form-control" path="slot" required="true">
                                <form:option value="8:00 AM - 9:00 AM">8:00 AM - 9:00 AM</form:option>
                                <form:option value="9:00 AM - 10:00 AM">9:00 AM - 10:00 AM</form:option>
                                <form:option value="10:00 AM - 11:00 AM">10:00 AM - 11:00 AM</form:option>
                                <form:option value="11:00 AM - 12:00 PM">11:00 AM - 12:00 PM</form:option>
                                <form:option value="1:00 PM - 2:00 PM">1:00 PM - 2:00 PM</form:option>
                                <form:option value="2:00 PM - 3:00 PM">2:00 PM - 3:00 PM</form:option>
                                <form:option value="3:00 PM - 4:00 PM">3:00 PM - 4:00 PM</form:option>
                                <form:option value="4:00 PM - 5:00 PM">4:00 PM - 5:00 PM</form:option>
                            </form:select>
                            <form:errors path="slot" style="color: red;"></form:errors>
                        </td>
                        <td style="width: 30%">
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