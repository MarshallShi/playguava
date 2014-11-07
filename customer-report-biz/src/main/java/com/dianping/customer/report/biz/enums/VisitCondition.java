package com.dianping.customer.report.biz.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum VisitCondition{

    EFFECTIVE_VISIT(1,"有效拜访(联系人为KP)"),
    HAS_PARTNER(2,"有陪访人"),
    SHOP_COOPERATOR_STATUS(3,"断约/未合作");

    private int code;
    private String desc;

    VisitCondition(int code, String desc) {
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

    public static VisitCondition getByCode(int code) {
        for (VisitCondition enumItem : values())
            if (enumItem.getCode() == code)
                return enumItem;
        return null;
    }

    public static List<Map<String,Object>> toList(){
        List<Map<String,Object>> resultList = new ArrayList<Map<String, Object>>();
        for(VisitCondition enumItem: values()){
            Map<String,Object> enumInfo = new HashMap<String, Object>();
            enumInfo.put(CODE,enumItem.code);
            enumInfo.put(DESC,enumItem.desc);
            resultList.add(enumInfo);
        }
        return resultList;
    }
}
