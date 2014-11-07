package com.dianping.customer.report.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HeartBeatFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        writeResponse((HttpServletResponse)servletResponse, "HelloWorld!I m alive");
    }

    private static final void writeResponse(HttpServletResponse response, String msg) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().print("{\"code\":200,\"msg\":\""+msg+"\"}");
    }

    @Override
    public void destroy() {
    }
}
