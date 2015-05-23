<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="jsp_includes/headCommon.jsp"/>
    <jsp:include page="jsp_includes/bootstrapAndJQueryScripts.jsp"/>
    <jsp:include page="jsp_includes/momentScripts.jsp"/>
    <jsp:include page="jsp_includes/datepickerScriptsAndCSS.jsp"/>
    <title>Create Meeting</title>
</head>
<body>
<jsp:include page="jsp_includes/navBar.jsp">
    <jsp:param name="active_button_number" value="3"/>
    <jsp:param name="is_logged" value="true"/>
    <jsp:param name="logged_user_id" value="${logged_user_id}"/>
    <jsp:param name="logged_user_first_name" value="${logged_user_first_name}"/>
    <jsp:param name="logged_user_last_name" value="${logged_user_last_name}"/>
</jsp:include>

<div class="container">
    <div class="starter-template">
        <form class="form-horizontal" action="meetingCreationCheck" method="post">
            <fieldset>

                <!-- Form Name -->
                <legend><h2><b>New meeting</b></h2></legend>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="name">Name</label>
                    <div class="col-md-4">
                        <input id="name" name="name" type="text" placeholder="name" class="form-control input-md" required="">

                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="description">Description</label>
                    <div class="col-md-4">
                        <input id="description" name="description" type="text" placeholder="description" class="form-control input-md">

                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="position_description">You position in the meeting</label>
                    <div class="col-md-4">
                        <input id="position_description" name="position_description" type="text" placeholder="position" class="form-control input-md">

                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="start">Start time</label>
                    <div class="col-md-4">
                        <input id="start" name="start" type="text" placeholder="choose start time" class="form-control input-md">
                        <script type="text/javascript">
                            $(function () {
                                $('#start').datetimepicker();
                            });
                            function startAndEndToUnixTimestamp(){
                                var start = document.getElementById('start').value;
                                var end = document.getElementById('end').value;

                                document.getElementById('start').value = moment(start).unix();
                                document.getElementById('end').value = moment(end).unix();
                            }
                        </script>
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="end">End time</label>
                    <div class="col-md-4">
                        <input id="end" name="end" type="text" placeholder="choose end time" class="form-control input-md">
                        <script type="text/javascript">
                            $(function () {
                                $('#end').datetimepicker();
                            });
                        </script>
                    </div>
                </div>

                <!-- Button (Double) -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="resetBtn"></label>
                    <div class="col-md-8">
                        <input type="reset" id="resetBtn" name="resetBtn" class="btn btn-warning" value="Reset">
                        <input type="submit" id="createBtn" name="createBtn" class="btn btn-success" value="Create meeting" onclick="startAndEndToUnixTimestamp()">
                    </div>
                </div>

            </fieldset>
        </form>
    </div>
</div>

<jsp:include page="jsp_includes/footer.jsp"/>
</body>
</html>