<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meetings</title>
</head>
<body>
<h3>Meetings for ${first_name} ${last_name}</h3>

<c:forEach var="i" items="${meetings_names}" varStatus="loop">
    <a href="${meetings_URLs[loop.index]}">${meetings_names[loop.index]}</a><br>
</c:forEach>

</body>
</html>