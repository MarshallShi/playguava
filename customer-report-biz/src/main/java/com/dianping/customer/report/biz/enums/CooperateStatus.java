package com.dianping.customer.report.biz.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum CooperateStatus {

    COOP_BREAK(0, "断约"),
    COOPING(1,"合作中"),
    NO_COOP(2, "未合作"),
    UN_FOUND(3,"暂无合作信息");

    private int code;
    private String desc;

    CooperateStatus(int code, String desc) {
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

    private static final String CODE = "value";
    private static final String DESC = "text";

    public static CooperateStatus getByCode(int code) {
        for (CooperateStatus enumItem : values())
            if (enumItem.getCode() == code)
                return enumItem;
        return null;
    }

    public static List<Map<String,Object>> toList(){
        List<Map<String,Object>> resultList = new ArrayList<Map<String, Object>>();
        for(CooperateStatus enumItem: values()){
            Map<String,Object> enumInfo = new HashMap<String, Object>();
            enumInfo.put(CODE,enumItem.code);
            enumInfo.put(DESC,enumItem.desc);
            resultList.add(enumInfo);
        }
        return resultList;
    }
}
