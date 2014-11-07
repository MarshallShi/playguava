package com.dianping.customer.report.biz.enums;

/**
 * Created by shenyoujun on 14/10/20.
 */
public enum BusinessUnitTypeEnum {
    BU_JIAOYI ( 1,"交易平台"),
    BU_TUIGUANG ( 2,"推广事业部"),
    BU_JIEHUN ( 3,"结婚事业部"),
    BU_YUDING ( 4,"预定事业部"),
    BU_WAIMAI ( 5,"外卖事业部");

    private int code;
    private String desc;

    BusinessUnitTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
