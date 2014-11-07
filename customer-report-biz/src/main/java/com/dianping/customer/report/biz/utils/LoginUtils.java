package com.dianping.customer.report.biz.utils;

import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by jrose on 14-6-11.
 */
public class LoginUtils {

    public static final String USER_INFO="__userInfo";

    public static int getLoginId() {
    	return -25332;
        //return getLoginId(ServletActionContext.getRequest());
    }

    public static int getLoginId(ServletRequest servletRequest) {
        try {
        	return -25332;
            //return Integer.parseInt(getLoginInfoArray(servletRequest)[1]);
        } catch (Exception e) {
            return 0;
        }
    }

    public static String[] getLoginInfoArray(ServletRequest servletRequest){
        String userInfoStr =((HttpServletRequest)servletRequest).getRemoteUser();
        String[] infos = userInfoStr.split("\\|");
        return infos;
    }

    public static String getLoginName() {
    	return "shiwei";
       //return getLoginName(ServletActionContext.getRequest());
    }

    public static String getLoginName(ServletRequest servletRequest) {
        try {
        	return "shiwei";
            //return getLoginInfoArray(servletRequest)[3];
        } catch (Exception e) {
            return "";
        }
    }
}
