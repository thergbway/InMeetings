<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Login Success Page</title>
</head>
<body>
<%
    String user = null;
    if (session.getAttribute("login") == null)
        response.sendRedirect("login");
    else
        user = ((String) session.getAttribute("login"));
%>
<h3>Hi <%=user%>, Login successful.</h3>
<a href="login">Logout</a>
</body>
</html>