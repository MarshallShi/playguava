package com.dianping.customer.report.biz.entity;

import java.util.Date;

public class Performance extends BaseEntity {
    private String roleId;
    private int typeId;
    private double volume;
    private Date performanceTime;

    public Performance() {}

    public Performance(int typeId, String roleId, double volume) {
        this.typeId = typeId;
        this.roleId = roleId;
        this.volume = volume;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public Date getPerformanceTime() {
        return performanceTime;
    }

    public void setPerformanceTime(Date performanceTime) {
        this.performanceTime = performanceTime;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
