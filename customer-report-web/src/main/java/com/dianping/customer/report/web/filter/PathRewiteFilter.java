package com.dianping.customer.report.web.filter;

import com.dianping.customer.report.biz.utils.ConfigUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: feipeng
 * Date: 14-7-9
 * Time: 下午2:49
 * To change this template use File | Settings | File Templates.
 */
public class PathRewiteFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
          chain.doFilter(new ServletRequestWrapper((HttpServletRequest)request),response);
    }

    @Override
    public void destroy() {
    }


    class ServletRequestWrapper extends HttpServletRequestWrapper {

        public ServletRequestWrapper(HttpServletRequest request) {
            super(request);

        }

        public String getRequestURI(){
            String uri = super.getRequestURI();
            if(StringUtils.isNotEmpty(ConfigUtils.getPath())){
                if(uri.indexOf("/"+ConfigUtils.getPath())==0){
                    return uri.substring(("/"+ConfigUtils.getPath()).length());
                }
            }
            return uri;
        }

        public String getServletPath(){
            String uri = super.getServletPath();
            if(StringUtils.isNotEmpty(ConfigUtils.getPath())){
                if(uri.indexOf("/"+ConfigUtils.getPath())==0){
                    return uri.substring(("/"+ConfigUtils.getPath()).length());
                }
            }
            return uri;
        }


    }



}
