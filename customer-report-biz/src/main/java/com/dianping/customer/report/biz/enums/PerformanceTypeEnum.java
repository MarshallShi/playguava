package com.dianping.customer.report.biz.enums;

public enum PerformanceTypeEnum {
    TRADING_VOLUME(1, "交易额"),
    BUSINESS_VOLUME(2, "营业额");

    private int code;
    private String desc;

    PerformanceTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PerformanceTypeEnum getByCode(int code) {
        for(PerformanceTypeEnum item : PerformanceTypeEnum.values()) {
            if(item.getCode() == code) {
                return item;
            }
        }
        return null;
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
