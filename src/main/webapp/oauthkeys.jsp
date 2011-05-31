<!DOCTYPE html PUBLIC"-// W3C//DTD HTML 4.01//EN">
<html>
  <head>
    <title>Activity Streams demo home server</title>
    <link href="styles.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
    <script type="text/javascript">
        $(function() {
            $.ajax({
                type: "GET",
                url: "/oauthkeys",
                success: function(data, textStatus, jqXHR) {
                    console.info(data);
                    $("input#consumerKey").val(data['consumerKey']);
                    $("input#consumerSecret").val(data['consumerSecret']);
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR.status);
                }
            });
        });

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

                var dataString = 'consumerKey='+ encodeURIComponent(consumerKey) + '&consumerSecret=' + encodeURIComponent(consumerSecret);

                $("#messageDiv").html("Processing ....").show();

                $.ajax({
                    type: "POST",
                    url: "oauthkeys",
                    data: dataString,
                    success: function(data, textStatus, xhr) {
                        $('#messageDiv').html("Keys successfully saved.")
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
                    Use the form below to provide the OAuth keys to be used for validating lifecycle events sent from
                    the app and to sign activity requests posted to Jive application gateway.
                </p>

                <div id="jsonDiv">
                    <form name="jsonForm">
                        <table>
                            <tbody>
                                <tr>
                                    <td><label for="consumerKey" id="consumerKeyLabel">Consumer Key:</label></td>
                                    <td><input type="text" size="40" name="consumerKey" id="consumerKey" value=""/></td>
                                </tr>
                                <tr>
                                    <td><label for="consumerSecret" id="consumerSecretLabel">Consumer Secret:</label></td>
                                    <td><input type="text" size="40" name="consumerSecret" id="consumerSecret" value=""/></td>
                                </tr>
                                <tr><td>
                                    <input type="submit" value="Use these OAuth keys" class="button" id="submitBtn"></td>
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
