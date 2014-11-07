package com.dianping.customer.report.biz.entity;

import java.util.Date;

public class SnapShot {
    private int id;
    private int createBy;
    private Date createTime;
    private String snapShotKey;
    private String snapShotData;
    private int typeId;

    public SnapShot() {}

    public SnapShot(String snapShotKey, String snapShotData, int typeId) {
        this.snapShotKey = snapShotKey;
        this.snapShotData = snapShotData;
        this.typeId = typeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSnapShotKey() {
        return snapShotKey;
    }

    public void setSnapShotKey(String snapShotKey) {
        this.snapShotKey = snapShotKey;
    }

    public String getSnapShotData() {
        return snapShotData;
    }

    public void setSnapShotData(String snapShotData) {
        this.snapShotData = snapShotData;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
