package com.dianping.customer.report.web.action.base;

import com.dianping.customer.report.biz.enums.PageTypeEnum;
import com.dianping.trade.data.enums.ReportQueryOrderByType;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ReportBase extends AjaxBase {
    private String asc;
    private String desc;
    private Integer pageSize;
    private Integer pageIndex;

    protected Map<ReportQueryOrderByType, Object> initOrderByMap() {
        Map<ReportQueryOrderByType, Object> result = new HashMap<ReportQueryOrderByType, Object>();
        if (StringUtils.isNotBlank(asc)) {
            result.put(ReportQueryOrderByType.ASC, asc);
        }
        if (StringUtils.isNotBlank(desc)) {
            result.put(ReportQueryOrderByType.DESC, desc);
        }
        return result;
    }

    protected Map<String, Object> initPageMap() {
        Map<String, Object> result = new HashMap<String, Object>();
        if(pageSize != null) {
            result.put(PageTypeEnum.PAGE_SIZE.getDesc(), pageSize);
        }
        if(pageIndex != null) {
            result.put(PageTypeEnum.PAGE_INDEX.getDesc(), pageIndex);
        }
        return result;
    }

    public void setAsc(String asc) {
        this.asc = asc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }
}
