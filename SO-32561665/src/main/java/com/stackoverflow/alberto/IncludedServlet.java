package com.stackoverflow.alberto;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alberto
 */
public class IncludedServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Always ignored (OK)
        response.setContentType("html/csv;charset=UTF-8");
        response.addHeader("Content-Disposition", "attachment; filename=testid.csv;");
        
        // XXX: This should affect the web server output
        // but current behaviour depends on javamelody's
        // CounterServletResponseWrapper implementation!
        HttpServletResponse topResponse = (HttpServletResponse) request.getAttribute(TopServlet.TOPLEVEL_RES_ATT_NAME);
        if (null != topResponse) {
            topResponse.setContentType("html/csv;charset=UTF-8");
            topResponse.addHeader("Content-Disposition", "attachment; filename=testid.csv;");
        } else {
            System.out.println("IncludedServlet: No toplevelResponse stored");
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("top11,top12,top13\n"
                    + "top21,top22,top23");
        }
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
