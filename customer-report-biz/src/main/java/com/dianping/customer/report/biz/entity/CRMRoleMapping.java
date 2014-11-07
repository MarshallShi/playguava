package com.dianping.customer.report.biz.entity;

/**
 * Created by zaza on 14-10-16.
 */
public class CRMRoleMapping {
    private String employeeId;
    private String crmRoleId;
    private int loginId;
    private String cityName;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCrmRoleId() {
        return crmRoleId;
    }

    public void setCrmRoleId(String crmRoleId) {
        this.crmRoleId = crmRoleId;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
