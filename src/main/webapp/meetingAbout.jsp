<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="jsp_includes/headCommon.jsp"/>
    <title>About Meeting</title>
</head>
<body>
<jsp:include page="jsp_includes/navBar.jsp">
    <jsp:param name="active_button_number" value="0"/>
    <jsp:param name="is_logged" value="true"/>
    <jsp:param name="logged_user_id" value="${logged_user_id}"/>
    <jsp:param name="logged_user_first_name" value="${logged_user_first_name}"/>
    <jsp:param name="logged_user_last_name" value="${logged_user_last_name}"/>
</jsp:include>

<div class="container">
    <div class="starter-template">
        <h2><b>About Meeting</b></h2>
        <h4>
            Name: ${meeting_name}<br>
            Description: ${meeting_description}<br>
            Start: ${meeting_start}<br>
            End: ${meeting_end}<br>
        </h4>
        <h3>People involved</h3><br>
        <div class="panel panel-default panel-custom-color2">
            <div class="panel-heading panel-heading-custom-color2">Managers:</div>
            <div class="list-group">
                <c:forEach var="i" items="${managers_names}" varStatus="loop">
                    <a href="${managers_URLs[loop.index]}" class="list-group-item">${managers_names[loop.index]}</a>
                </c:forEach>
            </div>
        </div>
        <br>
        <div class="panel panel-default panel-custom-color2">
            <div class="panel-heading panel-heading-custom-color2">Participants:</div>
            <div class="list-group">
                <c:forEach var="i" items="${participants_names}" varStatus="loop">
                    <a href="${participants_URLs[loop.index]}" class="list-group-item">${participants_names[loop.index]}</a>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<jsp:include page="jsp_includes/footer.jsp"/>
<jsp:include page="jsp_includes/bootstrapScripts.jsp"/>
</body>
</html>