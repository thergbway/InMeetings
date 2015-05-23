<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="jsp_includes/headCommon.jsp"/>
    <title>Registration success</title>
</head>
<body>
<jsp:include page="jsp_includes/navBar.jsp">
    <jsp:param name="is_logged" value="false"/>
</jsp:include>
<div class="container">
    <div class="starter-template">
        <p><h2>Congratulations!</h2></p><br>
        <h3>You have just registered!<br>Now it`s time to <a href="login">Sing in</a></h3>
    </div>
</div>
<jsp:include page="jsp_includes/footer.jsp"/>
<jsp:include page="jsp_includes/bootstrapAndJQueryScripts.jsp"/>
</body>
</html>
