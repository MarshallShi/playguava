package com.dianping.customer.report.web.filter;


import com.dianping.customer.report.biz.exceptions.OperationResult;

import javax.servlet.*;
import java.io.IOException;

public class OperationResultFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        OperationResult.getInstance().clearAllMessages();
        try{
            chain.doFilter(request, response);
        }finally {
            OperationResult.getInstance().clearAllMessages();
        }
    }

    @Override
    public void destroy() {
        
    }
}
