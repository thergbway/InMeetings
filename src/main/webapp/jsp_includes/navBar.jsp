<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/mainPage">
                <img src="${pageContext.request.contextPath}/brand_logo.png" width="25" height="25" alt="InMeetings">
            </a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <jsp:include page="menuPanel.jsp">
                <jsp:param name="active_button_number" value="${param.active_button_number}"/>
                <jsp:param name="is_logged" value="${param.is_logged}"/>
            </jsp:include>
            <jsp:include page="loggedUserPanel.jsp">
                <jsp:param name="is_logged" value="${param.is_logged}"/>
                <jsp:param name="user_id" value="${param.logged_user_id}"/>
                <jsp:param name="first_name" value="${param.logged_user_first_name}"/>
                <jsp:param name="last_name" value="${param.logged_user_last_name}"/>
            </jsp:include>
        </div>
    </div>
</nav>
