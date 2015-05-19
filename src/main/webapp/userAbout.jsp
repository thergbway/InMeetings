<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>About User</title>
</head>
<body>
<h2>User about</h2><br>
Name: ${first_name} ${last_name}<br>
Role: ${role_name}<br>

<h2>User meetings:</h2><br>
<h3>Managers:</h3><br>

<c:forEach var="i" items="${meetings_managing_names}" varStatus="loop">
    <a href="${meetings_managing_URLs[loop.index]}">${meetings_managing_names[loop.index]}</a><br>
</c:forEach>

<h3>Participant:</h3><br>

<c:forEach var="i" items="${meetings_participating_names}" varStatus="loop">
    <a href="${meetings_participating_URLs[loop.index]}">${meetings_participating_names[loop.index]}</a><br>
</c:forEach>
</body>
</html>
