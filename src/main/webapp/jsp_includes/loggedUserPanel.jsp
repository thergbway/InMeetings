<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${param.is_logged==true}">
    <ul class="nav navbar-nav navbar-right">
        <li>
            <a href="${pageContext.request.contextPath}/userAbout/id=${param.user_id}">${param.first_name} ${param.last_name}</a>
        </li>
        <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
    </ul>
</c:if>