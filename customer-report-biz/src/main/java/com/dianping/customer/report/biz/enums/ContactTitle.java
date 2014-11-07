package com.dianping.customer.report.biz.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ContactTitle {

    JURIDICAL("老板/股东","老板/股东"),
    PRESIDENT("店长","店长"),
    MANAGER("市场经理","市场经理"),
    SHOP_KEEPER("财务经理","财务经理"),
    OTHER("其他（如：店员）","其他（如：店员）");

    private String text;
    private String desc;

    ContactTitle(String text, String desc) {
        this.text = text;
        this.desc = desc;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private static final String CODE = "value";
    private static final String DESC = "text";

    public static List<Map<String,Object>> toList(){
        List<Map<String,Object>> resultList = new ArrayList<Map<String, Object>>();
        for(ContactTitle enumItem: values()){
            Map<String,Object> enumInfo = new HashMap<String, Object>();
            enumInfo.put(CODE,enumItem.text);
            enumInfo.put(DESC,enumItem.desc);
            resultList.add(enumInfo);
        }
        return resultList;
    }
}
