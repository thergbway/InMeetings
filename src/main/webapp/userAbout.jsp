<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="jsp_includes/headCommon.jsp"/>
    <title>About User</title>
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
        <h2><b>About User</b></h2>
        <h4>
            First name: ${first_name}<br>
            Last name: ${last_name}<br>
            Role: ${role_name}<br>
        </h4>
        <h3>Meetings</h3><br>
        <div class="panel panel-default panel-custom-color1">
            <div class="panel-heading panel-heading-custom-color1">Participates in:</div>
            <div class="list-group">
                <c:forEach var="i" items="${meetings_participating_names}" varStatus="loop">
                    <a href="${meetings_participating_URLs[loop.index]}" class="list-group-item">${meetings_participating_names[loop.index]}</a>
                </c:forEach>
            </div>
        </div>
        <br>
        <div class="panel panel-default panel-custom-color1">
            <div class="panel-heading panel-heading-custom-color1">Manages:</div>
            <div class="list-group">
                <c:forEach var="i" items="${meetings_managing_names}" varStatus="loop">
                    <a href="${meetings_managing_URLs[loop.index]}" class="list-group-item">${meetings_managing_names[loop.index]}</a>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<jsp:include page="jsp_includes/footer.jsp"/>
<jsp:include page="jsp_includes/bootstrapScripts.jsp"/>
</body>
</html>
