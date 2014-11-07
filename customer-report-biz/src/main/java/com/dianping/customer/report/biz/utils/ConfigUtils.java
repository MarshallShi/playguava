package com.dianping.customer.report.biz.utils;

import com.dianping.combiz.spring.util.PropertiesLoaderSupportUtils;

import java.io.IOException;
import java.util.*;

public class ConfigUtils {
    public static String getJsPath() {
        return PropertiesLoaderSupportUtils.getProperty("customer-report-web.js.path");
    }

    public static String getJsVersion() {
        return PropertiesLoaderSupportUtils.getProperty("customer-report-web.js.version");
    }

    public static String getPath() {
        return PropertiesLoaderSupportUtils.getProperty("customer-report-web.domain.path");
    }

    public static String getLoginOutUrl() {
        return PropertiesLoaderSupportUtils.getProperty("cas-server-webapp.logoutUrl");
    }

    public static String getServerName() {
        return PropertiesLoaderSupportUtils.getProperty("customer-report-web.serverName");
    }

    public static String getImageDomain() {
        return PropertiesLoaderSupportUtils.getProperty("ba-materialcenter-web.fileupload.fileServerUrl");
    }

    public static boolean isSendExceptionEmail() {
        return PropertiesLoaderSupportUtils.getBoolProperty("customer-report-web.isSendExceptionEmail", false);
    }

    public static String getProductCenterEmails() {
        return PropertiesLoaderSupportUtils.getProperty("customer-report-web.email.productcenter");
    }

    public static Map<String, String> getShopDetailSpecialCityConfigMap() {
        Map<String, String> result = new HashMap<String, String>();
        String configStr = PropertiesLoaderSupportUtils.getProperty("customer-report-web.shopDetailSpecialCityConfig");
        if(StringUtils.isNotBlank(configStr)) {
            try {
                result = JsonUtils.fromStr(configStr, Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static List<Integer> getSuperAdminList() {
        List<Integer> result = new LinkedList<Integer>();
        String superAdminConfig = PropertiesLoaderSupportUtils.getProperty("customer-report-web.superAdmin");
        if (StringUtils.isNotBlank(superAdminConfig)) {
            for (String superAdmin : superAdminConfig.split(",")) {
                result.add(Integer.valueOf(superAdmin));
            }
        }
        return result;
    }

    public static String getDefaultReportPageFrameSet() {
        return PropertiesLoaderSupportUtils.getProperty("customer-report-web.reportPage.frameSetConfig");
    }

    public static Map<String, List<Map>> getReportPageFrameSetPermissionConfigMap() {
        Map<String, List<Map>> result = new HashMap<String, List<Map>>();
        String permissionConfigStr = PropertiesLoaderSupportUtils.getProperty("customer-report-web.reportPage.frameSetPermissionConfig");
        if(StringUtils.isNotBlank(permissionConfigStr)) {
            try {
                result = JsonUtils.fromStr(permissionConfigStr, Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static int getSuperAdminLoginId() {
        return PropertiesLoaderSupportUtils.getIntProperty("customer-report-web.superAdmin.loginId", 0);
    }
}
