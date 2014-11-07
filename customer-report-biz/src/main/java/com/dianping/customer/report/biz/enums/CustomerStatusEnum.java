package com.dianping.customer.report.biz.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum CustomerStatusEnum {
    ALL(-1, "全部", ""),
    BOTH_OFFLINE(0, "双方不在线", "<span style='background-color: #bfbfbf;margin-right: 5px'>&nbsp;&nbsp;&nbsp;&nbsp;</span>"),
    DP_ONLY(1, "仅点评", "<span style='background-color: #ea700d;margin-right: 5px'>&nbsp;&nbsp;&nbsp;&nbsp;</span>"),
    MT_ONLY(2, "仅美团", "<span style='background-color: #00b050;margin-right: 5px'>&nbsp;&nbsp;&nbsp;&nbsp;</span>"),
    BOTH_ONLINE(3, "双方在线", "<span style='background-color: #ea700d;'>&nbsp;&nbsp;</span><span style='background-color: #00b050;margin-right: 5px'>&nbsp;&nbsp;</span>");

    private String desc;
    private int code;
    private String css;

    CustomerStatusEnum(int code, String desc, String css) {
        this.code = code;
        this.desc = desc;
        this.css = css;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getCss() {
        return css;
    }

    public static List<Map<String, Object>> toList() {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (CustomerStatusEnum enumItem : values()) {
            Map<String, Object> enumInfo = new HashMap<String, Object>();
            enumInfo.put("value", enumItem.getCode());
            enumInfo.put("text", enumItem.getDesc());
            enumInfo.put("css", enumItem.getCss());
            resultList.add(enumInfo);
        }
        return resultList;
    }
}
