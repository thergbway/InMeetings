<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="jsp_includes/headCommon.jsp"/>
    <title>My Meetings</title>
</head>
<body>
<jsp:include page="jsp_includes/navBar.jsp">
    <jsp:param name="active_button_number" value="1"/>
    <jsp:param name="is_logged" value="true"/>
    <jsp:param name="logged_user_id" value="${logged_user_id}"/>
    <jsp:param name="logged_user_first_name" value="${logged_user_first_name}"/>
    <jsp:param name="logged_user_last_name" value="${logged_user_last_name}"/>
</jsp:include>

<div class="container">
    <div class="starter-template">
        <h2><b>Your meetings</b></h2>

        <div class="panel panel-default panel-custom-color3">
            <div class="panel-heading panel-heading-custom-color3">You manage:</div>
            <div class="list-group">
                <c:forEach var="i" items="${meetings_user_managing_names}" varStatus="loop">
                    <div class="list-group-item">
                        <div class="btn-group" role="group">
                            <button type="button" class="btn btn-default"
                                    onclick="location.href='${meetings_user_managing_about_URLs[loop.index]}'">
                                    ${meetings_user_managing_names[loop.index]}
                            </button>
                            <button type="button" class="btn btn-danger"
                                    onclick="location.href='${meetings_user_managing_leave_URLs[loop.index]}'">
                                Leave
                            </button>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <br>

        <div class="panel panel-default panel-custom-color3">
            <div class="panel-heading panel-heading-custom-color3">You participate:</div>
            <div class="list-group">
                <c:forEach var="i" items="${meetings_user_participating_names}" varStatus="loop">
                    <div class="list-group-item">
                        <div class="btn-group" role="group">
                            <button type="button" class="btn btn-default"
                                    onclick="location.href='${meetings_user_participating_about_URLs[loop.index]}'">
                                    ${meetings_user_participating_names[loop.index]}
                            </button>
                            <button type="button" class="btn btn-danger"
                                    onclick="location.href='${meetings_user_participating_leave_URLs[loop.index]}'">
                                Leave
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