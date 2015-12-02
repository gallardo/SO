package com.stackoverflow.alberto;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class TopServlet extends HttpServlet {

    public static final String TOPLEVEL_RES_ATT_NAME = "TOPLEVEL_RES";
    public static final String INCLUDED_RES_PARAM_NAME = "INCLUDED_RES";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Wrapp to avoid Tomcat inserting the ApplicationHttpResponse in front of the top level response
        ServletResponse wRes = new HttpServletResponseWrapper(response);

        // Store the top level response to allow for changing headers later on
        request.setAttribute(TOPLEVEL_RES_ATT_NAME, response);
        
        String path = request.getParameter(INCLUDED_RES_PARAM_NAME);
        
        // Include in a wrapped response, to not alter the top level response
        getServletContext().getRequestDispatcher(path).include(request, wRes);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}