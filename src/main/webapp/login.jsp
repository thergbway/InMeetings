<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    Enter your credentials
    <br>
<form action="loginCheck" method="post">
    Login: <input type="text" name="login">
    <br>
    Password: <input type="password" name="password">
    <br>
    <input type="submit" value="Sign in">
</form>
</body>
</html>