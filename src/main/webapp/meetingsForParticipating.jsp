<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="jsp_includes/headCommon.jsp"/>
    <title>Join Meetings</title>
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
        <h2><b>Available meetings</b></h2>

        <div class="panel panel-default panel-custom-color4">
            <div class="panel-heading panel-heading-custom-color4">Meetings you may participate:</div>
            <div class="list-group">
                <c:forEach var="i" items="${meetings_names}" varStatus="loop">
                    <div class="list-group-item">
                        <div class="btn-group" role="group">
                            <button type="button" class="btn btn-default"
                                    onclick="location.href='${meetings_URLs_about[loop.index]}'">
                                    ${meetings_names[loop.index]}
                            </button>
                            <button type="button" class="btn btn-danger"
                                    onclick="location.href='${meetings_URLs_participate[loop.index]}'">
                                Join
                            </button>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<jsp:include page="jsp_includes/footer.jsp"/>
<jsp:include page="jsp_includes/bootstrapScripts.jsp"/>
</body>
</html>
