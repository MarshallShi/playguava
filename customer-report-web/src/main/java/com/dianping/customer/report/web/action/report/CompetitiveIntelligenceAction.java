package com.dianping.customer.report.web.action.report;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.dianping.customer.report.biz.annotation.AccessLogger;
import com.dianping.customer.report.biz.enums.PageTypeEnum;
import com.dianping.customer.report.biz.enums.ReportEnum;
import com.dianping.customer.report.biz.serviceagent.ReportServiceAgent;
import com.dianping.customer.report.biz.utils.DateUtils;
import com.dianping.customer.report.biz.utils.HintUtils;
import com.dianping.customer.report.web.action.base.ReportBase;
import com.dianping.trade.data.enums.ReportQueryOrderByType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class CompetitiveIntelligenceAction extends ReportBase {
    private String categoryName;
    private Integer cityId;
    private Integer districtId;
    private String cityName;
    private String districtName;
    private Integer regionId;

    @Autowired
    private ReportServiceAgent reportServiceAgent;

    @AccessLogger(reportName = "CompetitiveIntelligence.CategoryMonth")
    public String getByCategoryMonth() {
        checkFeasibilityWhenGetByCategoryMonth();
        String dateTime = DateUtils.format(DateUtils.getCurrentMonth(), DateUtils.monthFormat);
        Map<String, Object> conditionMap = initConditionMap(dateTime);
        setResponseData(reportServiceAgent.getReportByOrderAndPage(ReportEnum.CompetitiveIntelligenceByCategoryMonth, conditionMap, initOrderByMap(), initPageMap()).getItems());
        return SUCCESS;
    }

    @AccessLogger(reportName = "CompetitiveIntelligence.RegionMonth")
    public String getByRegionMonth() {
        String dateTime = DateUtils.format(DateUtils.getCurrentMonth(), DateUtils.monthFormat);
        Map<String, Object> conditionMap = initConditionMap(dateTime);
        setResponseData(reportServiceAgent.getReportByOrderAndPage(ReportEnum.CompetitiveIntelligenceByRegionMonth, conditionMap, initOrderByMap(), initPageMap()).getItems());
        return SUCCESS;
    }

    @AccessLogger(reportName = "CompetitiveIntelligence.ShopMonth")
    public String getByShopMonth(){
        String dateTime = DateUtils.format(DateUtils.getCurrentMonth(), DateUtils.monthFormat);
        Map<String, Object> conditionMap = initConditionMap(dateTime);
        setResponseData(reportServiceAgent.getReportByOrderAndPage(ReportEnum.CompetitiveIntelligenceByShopMonth, conditionMap, initOrderByMap(), initPageMap()));
        return SUCCESS;
    }

    @AccessLogger(reportName = "CompetitiveIntelligence.TrendGraphByMonth")
    public String getTrendGraphByMonth() {
        List<Map<String, Object>> result= reportServiceAgent.getReportByOrderAndPage(ReportEnum.CompetitiveIntelligenceByCategoryMonth, initGetTrendGraphConditionMap(), initGetTrendGraphOrderByMap(), initGetTrendGraphPageMap()).getItems();
        Collections.sort(result,new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> map1, Map<String, Object> map2) {
                return ((String)map1.get("time")).compareTo((String)map2.get("time"));
            }
        });
        setResponseData(result);
        return SUCCESS;
    }

    @AccessLogger(reportName = "CompetitiveIntelligence.TrendGraphByWeek")
    public String getTrendGraphByWeek() {
        List<Map<String, Object>> result = reportServiceAgent.getReportByOrderAndPage(ReportEnum.CompetitiveIntelligenceByCategoryWeek, initGetTrendGraphConditionMap(), initGetTrendGraphOrderByMap(), initGetTrendGraphPageMap()).getItems();
        Collections.sort(result,new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> map1, Map<String, Object> map2) {
                return ((Date)map1.get("time")).before((Date)map2.get("time"))?-1:1;
            }
        });
        setResponseData(result);
        return SUCCESS;
    }

    private void checkFeasibilityWhenGetByCategoryMonth() {
        HintUtils.assertRes(cityId != null, "请输入城市");
    }

    private Map<String, Object> initConditionMap(String dateTime) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(categoryName)) {
            result.put("category", categoryName);
        }
        if (cityId != null) {
            result.put("cityId", cityId);
        }
        if (districtId != null) {
            result.put("districtId", districtId);
        }
        if(regionId != null){
            result.put("regionId",regionId);
        }
        result.put("time", dateTime);
        return result;
    }

    private Map<String, Object> initGetTrendGraphConditionMap() {
        Map<String, Object> result = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(categoryName)) {
            result.put("category", categoryName);
        }
        if (cityId != null) {
            result.put("cityId", cityId);
        }
        if (StringUtils.isNotBlank(cityName)) {
            result.put("cityName", cityName);
        }
        if (districtId != null) {
            result.put("districtId", districtId);
        }
        if (StringUtils.isNotBlank(districtName)) {
            result.put("districtName", districtName);
        }
        return result;
    }

    private Map<ReportQueryOrderByType, Object> initGetTrendGraphOrderByMap() {
        Map<ReportQueryOrderByType, Object> result = new HashMap<ReportQueryOrderByType, Object>();
        result.put(ReportQueryOrderByType.DESC, "time");
        return result;
    }

    private Map<String, Object> initGetTrendGraphPageMap() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(PageTypeEnum.PAGE_SIZE.getDesc(), 12);
        result.put(PageTypeEnum.PAGE_INDEX.getDesc(), 1);
        return result;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
}
