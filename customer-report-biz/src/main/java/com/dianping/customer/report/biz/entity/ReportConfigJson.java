package com.dianping.customer.report.biz.entity;

import com.dianping.customer.report.biz.enums.ApplicationExceptionEnum;
import com.dianping.customer.report.biz.exceptions.ApplicationException;
import com.dianping.customer.report.biz.utils.JsonUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReportConfigJson extends BaseEntity {
    private String name;
    private int typeId;
    private String json;
    private Map<String, Object> jsonMap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Map<String, Object> getJsonMap() {
        if(jsonMap == null || jsonMap.isEmpty()) {
            try {
                jsonMap = JsonUtils.fromStr(getJson(), HashMap.class);
            } catch (IOException e) {
                throw new ApplicationException(ApplicationExceptionEnum.OPERATION_FAILED, e.getMessage());
            }
        }
        return jsonMap;
    }

    public String getReportName() {
        return getJsonMap().get("reportName").toString();
    }

    public Map<String, String> getCondition() {
        return (HashMap<String, String>) getJsonMap().get("condition");
    }

    public Map<String, String> getResult() {
        return (HashMap<String, String>) getJsonMap().get("result");
    }
}
