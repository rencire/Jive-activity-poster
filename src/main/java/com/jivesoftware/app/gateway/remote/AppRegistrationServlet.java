package com.jivesoftware.app.gateway.remote;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This servlet is fairly dumb - all it does it handle add/remove requests from jive instances. Data is stored
 * in instance variables in the ValidateAndPushServlet.
 */
public class AppRegistrationServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AppRegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String eventType = validate(req.getParameter("eventtype"), "eventtype parameter must be provided for app registration");
            String appUUID = validate(req.getParameter("opensocial_app_id"), "The request must be OAuth signed for app registration to succeed.");
            long userID = NumberUtils.toLong(validate(parseUserID(req.getParameter("opensocial_owner_id")), ""), -1);
            String instanceUUID = validate(parseInstanceID(req.getParameter("opensocial_owner_id")), "");

            if ("added".equals(eventType)) {
                logger.info("Received add registration - AppUUID=" + appUUID + ", userID=" + userID + ", " +
                        "instanceUUID=" + instanceUUID);
                ValidateAndPushServlet.add(Long.valueOf(userID), instanceUUID, appUUID);
            }
            else {
                logger.info("Received app deregistration - AppUUID=" + appUUID + ", userID=" + userID + ", " +
                        "instanceUUID=" + instanceUUID);
                ValidateAndPushServlet.remove(Long.valueOf(userID), instanceUUID, appUUID);
            }
       }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private String validate(String str, String errorMsg) {
        if (StringUtils.isBlank(str)) {
            throw new IllegalArgumentException(errorMsg);
        }

        return str;
    }

    private String parseUserID(String opensocialOwnerID) {
        if (opensocialOwnerID == null || opensocialOwnerID.indexOf('@') == -1) {
            return null;
        }

        return StringUtils.split(opensocialOwnerID, '@')[0];
    }

    private String parseInstanceID(String opensocialOwnerID) {
        if (opensocialOwnerID == null || opensocialOwnerID.indexOf('@') == -1) {
            return null;
        }

        return StringUtils.split(opensocialOwnerID, '@')[1];
    }
}
