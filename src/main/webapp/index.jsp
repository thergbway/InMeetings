<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><jsp:include page="jsp_includes/headCommon.jsp"/>
    <title>Welcome to InMeetings!</title>
</head>
<body>
<jsp:include page="jsp_includes/navBar.jsp">
    <jsp:param name="active_button_number" value="0"/>
    <jsp:param name="is_logged" value="false"/>
</jsp:include>
<div class="container">
    <div class="starter-template">
        <p><h2>Welcome to InMeetings!</h2></p>
        <h3><a href="login">Sign in</a><br>or<br></h3>
        <form class="form-horizontal" action="registrationCheck" method="post">
            <fieldset>

                <!-- Form Name -->
                <legend><h3><b>Sign up${error_message}</b></h3></legend>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="first_name">First name</label>
                    <div class="col-md-4">
                        <input id="first_name" name="first_name" type="text" placeholder="first name" class="form-control input-md" required="">
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="last_name">Last name</label>
                    <div class="col-md-4">
                        <input id="last_name" name="last_name" type="text" placeholder="last_name" class="form-control input-md" required="">
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="login">Login</label>
                    <div class="col-md-4">
                        <input id="login" name="login" type="text" placeholder="login" class="form-control input-md" required="">
                    </div>
                </div>

                <!-- Password input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="password">Password</label>
                    <div class="col-md-4">
                        <input id="password" name="password" type="password" placeholder="password" class="form-control input-md" required="">

                    </div>
                </div>

                <!-- Button (Double) -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="resetBtn"></label>
                    <div class="col-md-8">
                        <input type="reset" id="resetBtn" name="resetBtn" class="btn btn-warning" value="Reset">
                        <input type="submit" id="signUpBtn" name="signUpBtn" class="btn btn-success" value="Sign up">
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>
<jsp:include page="jsp_includes/footer.jsp"/>
<jsp:include page="jsp_includes/bootstrapAndJQueryScripts.jsp"/>
</body>
</html>
