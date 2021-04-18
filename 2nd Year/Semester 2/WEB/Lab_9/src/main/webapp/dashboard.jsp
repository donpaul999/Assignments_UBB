<%--
  Created by IntelliJ IDEA.
  User: paulcolta
  Date: 18/04/2021
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:genericpage>
    <jsp:body>
        <c:if test="${not empty sessionScope.username}">
            <h5>Hi, <c:out value="${sessionScope.username}" />!</h5>
        </c:if>

        <c:choose>
            <c:when test="${citiesList.size() > 0}">
                <ul class="list-group">
                <c:forEach items="${citiesList}" var="city">
                    <li class="list-group-item"><c:out value="${city.getName()}" /></li>
                </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <h1>No cities!</h1>
            </c:otherwise>
        </c:choose>
            <a href="logout" type="button" class="btn btn-danger mt-5">Log Out</a>
    </jsp:body>
</t:genericpage>