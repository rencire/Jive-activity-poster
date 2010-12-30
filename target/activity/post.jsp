<!DOCTYPE html PUBLIC"-// W3C//DTD HTML 4.01//EN">
<html>
  <head>
    <title>Activity Streams demo home server</title>
    <link href="styles.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
    <script type="text/javascript">
        $(function() {
            $("#messageDiv").hide();
            $(".button").click(function() {
                $("#messageDiv").hide();

                var consumerKey = $("input#consumerKey").val();
                if (consumerKey == "") {
                    $("#messageDiv").html("You must provide a consumer key to continue").show();
                    $("input#consumerKey").focus();
                    return false;
                }
                var consumerSecret = $("input#consumerSecret").val();
                if (consumerSecret == "") {
                    $("#messageDiv").html("You must provide a consumer secret to continue").show();
                    $("input#consumerSecret").focus();
                    return false;
                }
                var json = $("textarea#json").val();
                if (json == "") {
                    $("#messageDiv").html("You must provide the activity stream json to continue").show();
                    $("textarea#json").focus();
                    return false;
                }

                var dataString = 'consumerKey='+ encodeURIComponent(consumerKey)
                        + '&consumerSecret=' + encodeURIComponent(consumerSecret)
                        + '&targetType=' + encodeURIComponent($("select#targetType").val())
                        + '&uid=' + <%=request.getParameter("uid")%>
                        + '&json=' + encodeURIComponent(json);

                $("#messageDiv").html("Processing ....").show();

                $.ajax({
                    type: "POST",
                    url: "activity",
                    data: dataString,
                    success: function(xhr) {
                        $('#messageDiv').html("Form submitted successfully")
                                .append(" (").append(xhr.status).append(")")
                                .fadeIn(1500, function() {
                            $('#message');
                        });
                    },
                    error: function(xhr) {
                        $('#messageDiv').html("An error occurred")
                                .append("<br />Status code: ")
                                .append(xhr.status)
                                .append("<br/>Error message: ")
                                .append(xhr.getResponseHeader("X-Error-Message"))
                                .fadeIn(1500, function() {
                            $('#message');
                        });
                    }
                });
                return false;
            });
        });
    </script>
</head>
<body>
    <div id="wrap">
        <div id="header">
            <h1>Activity Streams Home Server Demo</h1>
        </div>
        <div id="content">
            <div class="section">
                <p>
                    Use the form below to enter an activity stream to be pushed to the app sandbox server. For examples of the
                    activity stream format make sure to read the
                    <a href="http://developers.jivesoftware.com/community/docs/DOC-1115">tutorial</a>
                </p>

                <div id="jsonDiv">
                    <form name="jsonForm">
                        <table>
                            <tbody>
                                <tr>
                                    <td><label for="consumerKey" id="consumerKeyLabel">Consumer Key:</label></td>
                                    <td><input type="text" size="40" name="consumerKey" id="consumerKey" value="4cf4e38b517d4d0fa14ca0bab9bf19ba"/></td>
                                </tr>
                                <tr>
                                    <td><label for="consumerSecret" id="consumerSecretLabel">Consumer Secret:</label></td>
                                    <td><input type="text" size="40" name="consumerSecret" id="consumerSecret" value="m1rPaMYZE3tC3odwz7RSPSDBMMI="/></td>
                                </tr>
                                <tr>
                                    <td><label for="targetType" id="targetTypeLabel">Target for activity stream:</label></td>
                                    <td><select name="targetType" id="targetType">
                                        <option value="0">Normal activity (Default)</option>
                                        <option value="1">Inbox activity</option>
                                    </select></td>
                                </tr>
                                <tr>
                                    <td><label for="json" id="jsonLabel">Activity Stream JSON:</label></td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <textarea name="json" id="json" rows="25" cols="70">
{
    "items":
    [{
        "title" : "Demonstrates posting activities from an external server.",
        "body" : "Install this app http://apphosting.jivesoftware.com/apps/jiveactivity/app.xml in app sandbox. More info here: https://developers.jivesoftware.com/community/docs/DOC-1119"
     }]
}
                                    </textarea></td>
                                </tr>
                                <tr><td>
                                    <input type="submit" value="Continue" class="button" id="submitBtn"></td>
                                </tr>
                                <tr><td colspan="2"><i id="messageDiv"></i></td></tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        <div id="footer">
            <small>Jive Software.</small>
        </div>
    </div>
</body>
</html>
