package com.dianping.customer.report.biz.enums;

import com.dianping.customer.report.biz.utils.DateUtils;

import java.util.*;

public enum TimeCondition {
    CUR_WEEK(1, "本周") {
        @Override
        public String getTimeSpace() {
            return String.format(TIME_SPACE, DateUtils.format(DateUtils.addDays(new Date(), -6), DateUtils.simpleDateFormat), DateUtils.format(DateUtils.addDays(new Date(), -1), DateUtils.simpleDateFormat));
        }
    },
    LAST_WEEK(2, "上周") {
        @Override
        public String getTimeSpace() {
            return String.format(TIME_SPACE, DateUtils.format(DateUtils.addDays(new Date(), -14), DateUtils.simpleDateFormat), DateUtils.format(DateUtils.addDays(new Date(), -7), DateUtils.simpleDateFormat));
        }
    },
    CUR_MONTH(3, "本月") {
        @Override
        public String getTimeSpace() {
            return String.format(TIME_SPACE, DateUtils.format(DateUtils.getCurrentMonth(), DateUtils.simpleDateFormat), DateUtils.format(DateUtils.addDays(new Date(), -1), DateUtils.simpleDateFormat));
        }
    },
    LAST_MONTH(4, "上月") {
        @Override
        public String getTimeSpace() {
            return String.format(TIME_SPACE, DateUtils.format(DateUtils.addMonth(DateUtils.getCurrentMonth(), -1), DateUtils.simpleDateFormat), DateUtils.format(DateUtils.addDays(DateUtils.getCurrentMonth(), -1), DateUtils.simpleDateFormat));
        }
    };

    private int code;
    private String desc;

    TimeCondition(int code, String desc) {
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

    private static final String TIME_SPACE = "%s 至 %s";
    private static final String CODE = "value";
    private static final String DESC = "text";
    private static final String SPACE = "range";
    public abstract String getTimeSpace();

    public static TimeCondition getByCode(int code) {
        for (TimeCondition enumItem : values())
            if (enumItem.getCode() == code)
                return enumItem;
        return null;
    }

    public static List<Map<String, Object>> toList() {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (TimeCondition enumItem : values()) {
            Map<String, Object> enumInfo = new HashMap<String, Object>();
            enumInfo.put(CODE, enumItem.code);
            enumInfo.put(DESC, enumItem.desc);
            enumInfo.put(SPACE, enumItem.getTimeSpace());
            resultList.add(enumInfo);
        }
        return resultList;
    }
}
