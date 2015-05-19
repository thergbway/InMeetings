<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Meeting</title>
</head>
<body>
<h3>Create Meeting:</h3><br>

<form action="meetingCreationCheck" method="post">
    Name: <input type="text" name="name" accept-charset="utf-8"><br>
    Description: <input type="text" name="description" accept-charset="utf-8"><br>
    You position in meeting: <input type="text" name="position_description" accept-charset="utf-8"><br>
    Start time: <input type="text" name="start" value="1434998781"><br>
    End time: <input type="text" name="end" value="1435257981"><br>
    <input type="submit" value="Create meeting">
</form>

</body>
</html>