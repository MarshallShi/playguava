package com.dianping.customer.report.biz.enums;

/**
 * Created by gelin on 14-10-17.
 */
public enum PageFrameSetTypeEnum {
    Info("info", "竞争情报"),
    Customer("customer", "重点客户"),
    CustomerSummary("customersummary", "本地汇总");

    private String name;
    private String desc;

    PageFrameSetTypeEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public static PageFrameSetTypeEnum getByName(String name) {
        for(PageFrameSetTypeEnum item : values()) {
            if(item.getName().toLowerCase().equals(name.toLowerCase())) {
                return item;
            }
        }
        return null;
    }
}
