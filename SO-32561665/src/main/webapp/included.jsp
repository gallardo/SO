<%
    HttpServletResponse toplevelResponse = (HttpServletResponse) request.getAttribute(
            com.stackoverflow.alberto.TopServlet.TOPLEVEL_RES_ATT_NAME);

    // Always ignored (OK)
    response.setContentType("html/csv");
    response.addHeader("Content-Disposition", "attachment; filename=testid.csv;");

    if (null != toplevelResponse) {
        // XXX: This should affect the web server output
        // but current behaviour depends on javamelody's
        // CounterServletResponseWrapper implementation!
        toplevelResponse.setContentType("html/csv");
        toplevelResponse.addHeader("Content-Disposition", "attachment; filename=testid.csv;");
    } else {
        System.out.println("included.jsp: No toplevelResponse stored");
    }
%>top11,top12,top13
top21,top22,top23