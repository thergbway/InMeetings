<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="jsp_includes/headCommon.jsp"/>
    <title>Login</title>
</head>
<body>
<jsp:include page="jsp_includes/navBar.jsp">
    <jsp:param name="is_logged" value="false"/>
</jsp:include>
<div class="container">
    <div class="starter-template">
        <p><h2>Please Login to continue</h2></p>
        <form class="form-horizontal" action="loginCheck" method="post">
            <fieldset>

                <!-- Form Name -->
                <legend><h2><b>Enter your credentials${error_message}</b></h2></legend>

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
                        <input type="submit" id="loginBtn" name="loginBtn" class="btn btn-success" value="Login">
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