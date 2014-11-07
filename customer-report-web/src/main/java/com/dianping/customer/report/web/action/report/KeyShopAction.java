package com.dianping.customer.report.web.action.report;

import com.dianping.customer.report.biz.annotation.AccessLogger;
import com.dianping.customer.report.biz.dto.RoleTreeNode;
import com.dianping.customer.report.biz.enums.ReportEnum;
import com.dianping.customer.report.web.action.base.ReportBase;
import com.dianping.customer.report.biz.service.PermissionService;
import com.dianping.customer.report.biz.serviceagent.ReportServiceAgent;
import com.dianping.customer.report.biz.utils.ConfigUtils;
import com.dianping.customer.report.biz.utils.HintUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyShopAction extends ReportBase {
    private Integer cityId;
    private String categoryName;
    private String time;
    private String cityName;
    private Integer customerStatus;
    private Integer shopId;
    private String roleId;
    private String status;

    @Autowired
    private ReportServiceAgent reportServiceAgent;
    @Autowired
    private PermissionService permissionService;

    @AccessLogger(reportName = "KeyShop.LocalSummary")
    public String getLocalSummary() {
        checkFeasibilityWhenGetLocalSummary();
        setResponseData(reportServiceAgent.getReportByOrderAndPage(ReportEnum.KeyShopQty, initGetLocalSummaryConditionMap(), initOrderByMap(), initPageMap()).getItems());
        return SUCCESS;
    }

    @AccessLogger(reportName = "KeyShop.LocalDetail")
    public String getLocalDetail() {
//        checkFeasibilityWhenGetLocalDetail();
        setResponseData(reportServiceAgent.getReportByOrderAndPage(ReportEnum.KeyShopDetail, initGetLocalDetailConditionMap(), initOrderByMap(), initPageMap()));
        return SUCCESS;
    }

    @AccessLogger(reportName = "KeyShop.LocalShopDetail")
    public String getLocalShopDetail() {
        setResponseData(reportServiceAgent.getReportByOrderAndPage(ReportEnum.KeyLocalShopDetail, initGetLocalDetailConditionMap(), initOrderByMap(), initPageMap()).getItems());
        return SUCCESS;
    }

    @AccessLogger(reportName = "KeyShop.SaleDetailSummary")
    public String getSaleDetailSummary() {
        checkFeasibilityWhenGetSaleDetailSummary();
        setResponseData(reportServiceAgent.getReportByOrderAndPage(ReportEnum.KeyShopSummaryOfDp, initGetShopDetailConditionMap(), initOrderByMap(), initPageMap()).getItems());
        return SUCCESS;
    }

    @AccessLogger(reportName = "KeyShop.SaleDetailsByStatus")
    public String getSaleDetailsByStatus() {
//        setResponseData(reportServiceAgent.getReportByOrderAndPage(ReportEnum.KeyShopSaleDetailsByStatus, initGetShopDetailConditionMap(), initOrderByMap(), initPageMap()));
        return SUCCESS;
    }

    private void checkFeasibilityWhenGetLocalSummary() {
        HintUtils.assertRes(StringUtils.isNotBlank(time), "请选择月份");
        HintUtils.assertRes(StringUtils.isNotBlank(categoryName), "请选择品类");
    }

    private void checkFeasibilityWhenGetLocalDetail() {
        HintUtils.assertRes(StringUtils.isNotBlank(time), "请选择月份");
        HintUtils.assertRes(cityId != null, "请输入城市");
//        HintUtils.assertRes(StringUtils.isNotBlank(categoryName), "请选择品类");
    }

    private void checkFeasibilityWhenGetSaleDetailSummary() {
        HintUtils.assertRes(StringUtils.isNotBlank(time), "请选择月份");
        HintUtils.assertRes(StringUtils.isNotBlank(categoryName), "请选择品类");
    }

    private Map<String, Object> initGetLocalSummaryConditionMap() {
        Map<String, Object> result = new HashMap<String, Object>();
        if (cityId != null) {
            result.put("cityId", cityId);
        }
        if (StringUtils.isNotBlank(categoryName)) {
            result.put("category", categoryName);
        }
        if (StringUtils.isNotBlank(time)) {
            result.put("time", time);
        }
        return result;
    }

    private Map<String, Object> initGetShopDetailConditionMap() {
        Map<String, Object> result = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(categoryName)) {
            result.put("category", categoryName);
        }
        if (StringUtils.isNotBlank(cityName)) {
            if (ConfigUtils.getShopDetailSpecialCityConfigMap().containsKey(cityName)) {
                result.put("cityName", ConfigUtils.getShopDetailSpecialCityConfigMap().get(cityName));
            } else {
                result.put("cityName", cityName);
            }
        }
        if (StringUtils.isNotBlank(roleId)) {
//            RoleTreeNode loginRoleTreeNode = permissionService.getRoleTreeNodeByRoleId(roleId);
//            List<String> roleList = new ArrayList<String>();
//            if(loginRoleTreeNode != null){
//                List<RoleTreeNode> childList = loginRoleTreeNode.getChildList();
//                if(CollectionUtils.isNotEmpty(childList)){
//                    for (RoleTreeNode roleTreeNode : childList){
//                        roleList.add(roleTreeNode.getData().getRoleId());
//                    }
//                }
//            }
            result.put("roleId", roleId);
        }
        if (StringUtils.isNotBlank(time)) {
            result.put("time", time);
        }
        if (StringUtils.isNotBlank(status)) {
            result.put("status", status);
        }
        return result;
    }

    private Map<String, Object> initGetLocalDetailConditionMap() {
        Map<String, Object> result = new HashMap<String, Object>();
        if (cityId != null) {
            result.put("cityId", cityId);
        }
        if (StringUtils.isNotBlank(categoryName)) {
            result.put("category", categoryName);
        }
        if (customerStatus != null) {
            result.put("isDpShop", customerStatus % 2);
            result.put("isMtShop", customerStatus / 2);
        }
        if (StringUtils.isNotBlank(time)) {
            result.put("time", time);
        }
        if (shopId != null) {
            result.put("shopId", shopId);
        }
        return result;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCustomerStatus(Integer customerStatus) {
        this.customerStatus = customerStatus;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
