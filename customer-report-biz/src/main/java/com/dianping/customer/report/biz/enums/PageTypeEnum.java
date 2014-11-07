package com.dianping.customer.report.biz.enums;

public enum PageTypeEnum {

    PAGE_INDEX("pageIndex"),
    PAGE_SIZE("pageSize");

    private String desc;

    PageTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
