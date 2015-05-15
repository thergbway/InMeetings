<%@ page import="com.inmeetings.persistence.dao.entities.Role" %>
<%@ page import="com.inmeetings.persistence.dao.implementations.RoleDAOImpl" %>
<%@ page import="com.inmeetings.persistence.dao.interfaces.RoleDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.function.Consumer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Role Manager</title>
</head>
<body>

Hello from RoleServlet! Count of Roles from Session = ${sessionScope.count}<br/>
Hello from RoleServlet! Count of Roles from Request = ${requestScope.count}<br/>
Hello from RoleServlet! Count of Roles from Session2 = <%=session.getAttribute("count")%><br/>
Hello from RoleServlet! Count of Roles from Request2 = <%=request.getAttribute("count")%><br/>


</body>
</html>