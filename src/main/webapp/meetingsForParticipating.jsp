<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Join Meetings</title>
</head>
<body>
<h2>Meetings to join:</h2><br>

<c:forEach var="i" items="${meetings_names}" varStatus="loop">
    Meeting: ${meetings_names[loop.index]}
    <a href="${meetings_URLs_about[loop.index]}">About</a>
    <a href="${meetings_URLs_participate[loop.index]}">Participate</a><br>
</c:forEach>
</body>
</html>
