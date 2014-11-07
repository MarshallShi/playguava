package com.dianping.customer.report.biz.entity;

/**
 * Created by zaza on 14-10-16.
 */
public class CRMRoles {
    private String crmRoleId;
    private String name;
    private String level;
    private String category;
    private String responsibilityCityList;
    private String parentId;
    private String status;

    public String getCrmRoleId() {
        return crmRoleId;
    }

    public void setCrmRoleId(String crmRoleId) {
        this.crmRoleId = crmRoleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getResponsibilityCityList() {
        return responsibilityCityList;
    }

    public void setResponsibilityCityList(String responsibilityCityList) {
        this.responsibilityCityList = responsibilityCityList;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
