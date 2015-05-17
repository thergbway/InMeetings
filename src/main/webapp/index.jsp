<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
    <h3>Welcome to InMeetings</h3><br>

    <a href="login">Sign in</a><br>
    or<br>
    Sign up<br>
    <form action="registrationCheck" method="post">
        First name: <input type="text" name="first_name">
        <br>
        Last name: <input type="text" name="last_name">
        <br>
        Login: <input type="text" name="login">
        <br>
        Password: <input type="password" name="password">
        <br>
        <input type="submit" value="Sign up">
    </form>
</body>
</html>
