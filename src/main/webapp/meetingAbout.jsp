<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>About Meeting</title>
</head>
<body>
<h2>Meeting about</h2><br>

Name: ${meeting_name}<br>
Description: ${meeting_description}<br>
Start: ${meeting_start}<br>
End: ${meeting_end}<br>

<h3>Managers:</h3><br>
<c:forEach var="i" items="${managers_names}" varStatus="loop">
    <a href="${managers_URLs[loop.index]}">${managers_names[loop.index]}</a><br>
</c:forEach>

<h3>Participants:</h3><br>
<c:forEach var="i" items="${participants_names}" varStatus="loop">
    <a href="${participants_URLs[loop.index]}">${participants_names[loop.index]}</a><br>
</c:forEach>
</body>
</html>