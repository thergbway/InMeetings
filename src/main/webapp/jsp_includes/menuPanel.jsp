<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul class="nav navbar-nav">
    <li <c:if test="${param.active_button_number==1}">class="active"</c:if>
            ><a href="meetingsAll">My Meetings<span class="sr-only">(current)</span></a></li>
    <li <c:if test="${param.active_button_number==2}">class="active"</c:if>
            ><a href="meetingsForParticipating">Find Meetings</a></li>
    <li <c:if test="${param.active_button_number==3}">class="active"</c:if>
            ><a href="meetingCreate">New Meeting</a></li>
</ul>