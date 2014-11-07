package com.dianping.customer.report.biz.enums;

import com.dianping.customer.report.biz.entity.PageFrameSet;
import com.dianping.customer.report.biz.utils.PageFrameSetUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by gelin on 14-10-17.
 */
public enum PageFrameSetPermissionConfigTypeEnum {
    Level(1, "level") {
        @Override
        public void handlePageFrameSetPermission(List<PageFrameSet> pageFrameSetList, String pageFrameSetPath, Map<String, Object> data, Object value) {
            if (((Integer) value) > ((Integer) data.get("level"))) {
                PageFrameSetUtils.removeByPath(pageFrameSetList, pageFrameSetPath);
            }
        }
    };

    private int code;
    private String desc;

    PageFrameSetPermissionConfigTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PageFrameSetPermissionConfigTypeEnum getByCode(int code) {
        for (PageFrameSetPermissionConfigTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }

    public static PageFrameSetPermissionConfigTypeEnum getByDesc(String desc) {
        for (PageFrameSetPermissionConfigTypeEnum item : values()) {
            if (item.getDesc().toLowerCase().equals(desc.toLowerCase())) {
                return item;
            }
        }
        return null;
    }

    public abstract void handlePageFrameSetPermission(List<PageFrameSet> pageFrameSetList, String pageFrameSetPath, Map<String, Object> data, Object value);

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
