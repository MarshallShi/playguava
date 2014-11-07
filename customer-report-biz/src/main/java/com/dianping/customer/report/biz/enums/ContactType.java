package com.dianping.customer.report.biz.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ContactType {

    IS_KP( 1, "isKP"),
    NOT_KP (0,"NotKP");

    private int code;
    private String desc;

    ContactType(int code, String desc) {
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

    public static ContactType getByCode(int code) {
        for (ContactType enumItem : values())
            if (enumItem.getCode() == code)
                return enumItem;
        return null;
    }

    public static List<Map<String,Object>> toList(){
        List<Map<String,Object>> resultList = new ArrayList<Map<String, Object>>();
        for(ContactType enumItem: values()){
            Map<String,Object> enumInfo = new HashMap<String, Object>();
            enumInfo.put(CODE,enumItem.code);
            enumInfo.put(DESC,enumItem.desc);
            resultList.add(enumInfo);
        }
        return resultList;
    }
}
