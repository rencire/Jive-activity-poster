package com.jivesoftware.app.gateway.remote;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OAuthKeysServlet extends HttpServlet {

    public static String ConsumerKey = "4cf4e38b517d4d0fa14ca0bab9bf19ba";
    public static String ConsumerSecret = "m1rPaMYZE3tC3odwz7RSPSDBMMI=";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        ServletOutputStream out = resp.getOutputStream();
        out.print("{ \"consumerKey\": \"" + ConsumerKey + "\", \"consumerSecret\": \"" + ConsumerSecret + "\" }");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConsumerKey = req.getParameter("consumerKey");
        ConsumerSecret = req.getParameter("consumerSecret");
        if(ConsumerKey == null || ConsumerSecret == null) resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Consumer key and secret are required");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
