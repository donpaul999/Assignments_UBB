<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:genericpage>
    <jsp:body>
        <c:if test="${(not empty error_log_in)}">
            <div class="alert alert-warning text-center" role="alert">${error_log_in}</div>
        </c:if>
        <form method="POST" action="login">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" value="admin" name="username" placeholder="Enter Username" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="text" class="form-control" id="password" value="admin" name="password" placeholder="Enter Password" required>
            </div>
            <button type="submit" class="btn btn-primary mt-5">Submit</button>
            <a href="javascript:history.back()" type="button" class="btn btn-danger mt-5">Back</a>
        </form>
    </jsp:body>
</t:genericpage>