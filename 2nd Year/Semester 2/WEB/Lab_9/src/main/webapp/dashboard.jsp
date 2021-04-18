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
        <div id="myModal" class="modal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Are you sure?</h5>
                        <button type="button" class="close close-delete" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Are you sure you want to remove this City?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn accept-delete btn-primary">Yes</button>
                        <button type="button" class="btn btn-secondary close-delete" data-dismiss="modal">Cancel</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-6">
                <c:choose>
                    <c:when test="${citiesList.size() > 0}">
                        <ul class="list-group cities-list">
                        <c:forEach items="${citiesList}" var="city">
                            <li data-id='<c:out value="${city.getId()}"/>' data-name='<c:out value="${city.getName()}" />' class="list-group-item city-box"><c:out value="${city.getName()}" /></li>
                        </c:forEach>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <h1>No cities!</h1>
                    </c:otherwise>
                </c:choose>
            </div>
                <div class="selected-cities-div mt-10 col-6">
                    <h5>Selected Cities</h5>
                    <ul class="selected-cities">
                    </ul>
                </div>
        </div>
            <a href="logout" type="button" class="btn btn-danger mt-5">Log Out</a>
    </jsp:body>
</t:genericpage>