<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${param.is_logged==true}">
    <ul class="nav navbar-nav">
        <li <c:if test="${param.active_button_number==1}">class="active"</c:if>>
            <a href="${pageContext.request.contextPath}/meetingsAll">My Meetings</a>
        </li>
        <li <c:if test="${param.active_button_number==2}">class="active"</c:if>>
            <a href="${pageContext.request.contextPath}/meetingsForParticipating">Find Meetings</a>
        </li>
        <li <c:if test="${param.active_button_number==3}">class="active"</c:if>>
            <a href="${pageContext.request.contextPath}/meetingCreate">New Meeting</a>
        </li>
    </ul>
</c:if>