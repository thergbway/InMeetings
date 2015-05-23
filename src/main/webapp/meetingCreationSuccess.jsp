<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="jsp_includes/headCommon.jsp"/>
    <title>Meeting creation success</title>
</head>

<body>
<jsp:include page="jsp_includes/navBar.jsp">
    <jsp:param name="active_button_number" value="0"/>
    <jsp:param name="is_logged" value="true"/>
    <jsp:param name="logged_user_id" value="${logged_user_id}"/>
    <jsp:param name="logged_user_first_name" value="${first_name}"/>
    <jsp:param name="logged_user_last_name" value="${last_name}"/>
</jsp:include>

<div class="container">
    <div class="starter-template">
        <p><h2>Meeting was successfully created</h2></p>
        <h4>Go to <a href="${pageContext.request.contextPath}/meetingsAll">My Meetings</a></h4>
    </div>
</div>

<jsp:include page="jsp_includes/footer.jsp"/>
<jsp:include page="jsp_includes/bootstrapAndJQueryScripts.jsp"/>
</body>
</html>