package com.stackoverflow.alberto;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author alberto
 */
public class TopServletIT {
    private static final String LOCALHOST = "http://localhost:8080/";
    private static final String CONTEXT = "testinclude/";
    private static final String TOPSERVLET_URL = LOCALHOST + CONTEXT + "TopServlet";
    private static final String INCLUDEDJSP = "included.jsp";
    private static final String INCLUDEDSERVLET = "IncludedServlet";
    private static final String INCLUDEDJSP_URL = LOCALHOST + CONTEXT + INCLUDEDJSP;
    private static final String INCLUDEDSERVLET_URL = LOCALHOST + CONTEXT + INCLUDEDSERVLET;
    private static final String MIME_CSV = "html/csv";
    private static final String CONTENT_DISP = "Content-Disposition";

    @Test
    public void testGet_includedjsp() throws Exception {
        try (final WebClient webClient = new WebClient()) {
            final Page page = webClient.getPage(INCLUDEDJSP_URL);
            Assert.assertEquals(MIME_CSV, page.getWebResponse().getContentType());
            Assert.assertTrue(page.getWebResponse().getResponseHeaderValue(CONTENT_DISP).contains("attachment"));
        }
    }

    @Test
    public void testGet_IncludedServlet() throws Exception {
        try (final WebClient webClient = new WebClient()) {
            final Page page = webClient.getPage(INCLUDEDSERVLET_URL);
            Assert.assertEquals(MIME_CSV, page.getWebResponse().getContentType());
            Assert.assertTrue(page.getWebResponse().getResponseHeaderValue(CONTENT_DISP).contains("attachment"));
        }
    }

    @Test
    public void testGet_includedjsp_via_TopServlet() throws Exception {
        try (final WebClient webClient = new WebClient()) {
            final Page page = webClient.getPage(TOPSERVLET_URL + "?" + TopServlet.INCLUDED_RES_PARAM_NAME + "=/" + INCLUDEDJSP);
            Assert.assertEquals(MIME_CSV, page.getWebResponse().getContentType());
        }
    }

    @Test
    public void testGet_IncludedServlet_via_TopServlet() throws Exception {
        try (final WebClient webClient = new WebClient()) {
            final Page page = webClient.getPage(TOPSERVLET_URL + "?" + TopServlet.INCLUDED_RES_PARAM_NAME + "=/" + INCLUDEDSERVLET);
            Assert.assertEquals(MIME_CSV, page.getWebResponse().getContentType());
        }
    }
}
