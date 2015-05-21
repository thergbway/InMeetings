<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <link rel="icon" href="../../favicon.ico">

    <title>Main Page</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="custom.css" rel="stylesheet">
</head>
<body>

<jsp:include page="jsp_includes/navBar.jsp">
    <jsp:param name="active_button_number" value="0"/>
    <jsp:param name="is_logged" value="true"/>
    <jsp:param name="logged_user_id" value="${logged_user_id}"/>
    <jsp:param name="logged_user_first_name" value="${first_name}"/>
    <jsp:param name="logged_user_last_name" value="${last_name}"/>
</jsp:include>


<div class="container">
    <div class="starter-template">
        <p align="center"><h2>Welcome to InMeetings!</h2></p>
        <h4>No.1 site to get involved in a big variety of the meetings world</h4>
    </div>
</div>

<nav class="navbar navbar-default navbar-fixed-bottom">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
            <ul class="nav navbar-nav navbar-right">
                <li><a class="disabled-link">InMeetings &copy; Andrey Selivanov</a></li>
                <li><a href="mailto:thergbworld@gmail.com">thergbworld@gmail.com</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
