package com.dianping.customer.report.web.action.report;

import com.dianping.customer.report.biz.annotation.AccessLogger;
import com.dianping.customer.report.biz.dto.RoleTreeNode;
import com.dianping.customer.report.biz.entity.Performance;
import com.dianping.customer.report.biz.enums.PerformanceTypeEnum;
import com.dianping.customer.report.biz.enums.ReportEnum;
import com.dianping.customer.report.biz.service.PerformanceService;
import com.dianping.customer.report.biz.service.PermissionService;
import com.dianping.customer.report.biz.serviceagent.ReportServiceAgent;
import com.dianping.customer.report.biz.utils.DateUtils;
import com.dianping.customer.report.biz.utils.HintUtils;
import com.dianping.customer.report.web.action.base.ReportBase;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yonney.yang
 * Date: 14-10-21
 * Time: 上午11:20
 * To change this template use File | Settings | File Templates.
 */
public class KpiSummaryAction extends ReportBase {
    private String roleId;
    private String time;
    private String type;

    @Autowired
    private ReportServiceAgent reportServiceAgent;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private PerformanceService performanceService;

    @AccessLogger(reportName = "KpiSummary.KpiSummary")
    public String getKpiSummary() {
        checkFeasibilityWhenGetKpiInfo();
        setResponseData(transferSummaryResult(reportServiceAgent.getReportByOrderAndPage(ReportEnum.KpiByMonth, initGetKpiByMonthConditionMap(), initOrderByMap(), initPageMap()).getItems()));
        return SUCCESS;
    }

    @AccessLogger(reportName = "KpiSummary.KpiDetails")
    public String getKpiDetails() {
        RoleTreeNode loginRoleTreeNode = permissionService.getRoleTreeNodeByRoleId(roleId);
        Boolean bool = loginRoleTreeNode.isGroupLeader();
        List<Map<String,Object>> result = new ArrayList<Map<String, Object>>();
        //1.判断是否还有叶子节点，没有则查销售的
        if (!bool) {
            //叶子节点  判断周还是全部
            Map<String,Object> condition = initGetKpiLeafDetailConditionMap(loginRoleTreeNode);
            if(CollectionUtils.isNotEmpty((List<Integer>)condition.get("loginId"))){
                if ("all".equals(type)) {
                    result = transferResult(reportServiceAgent.getReportByOrderAndPage(ReportEnum.KpiLeafByMonth, condition, initOrderByMap(), initPageMap()).getItems());
                } else {
                    result = transferResult(reportServiceAgent.getReportByOrderAndPage(ReportEnum.KpiLeafByWeek, condition, initOrderByMap(), initPageMap()).getItems());
                }
            }
        } else {
            //非叶子节点  判断周还是全部
            Map<String,Object> condition = initGetKpiDetailsConditionMap(loginRoleTreeNode);
            if ("all".equals(type)) {
                result = transferResult(reportServiceAgent.getReportByOrderAndPage(ReportEnum.KpiByMonth, condition, initOrderByMap(), initPageMap()).getItems());
            } else {
                result = transferResult(reportServiceAgent.getReportByOrderAndPage(ReportEnum.KpiByWeek, condition, initOrderByMap(), initPageMap()).getItems());
            }
        }
        setResponseData(result);
        return SUCCESS;
    }

    private void checkFeasibilityWhenGetKpiInfo() {
        HintUtils.assertRes(StringUtils.isNotBlank(time), "请选择月份");
        HintUtils.assertRes(StringUtils.isNotBlank(roleId), "角色不能为空");
    }

    private List<Map<String,Object>> transferSummaryResult(List<Map<String,Object>> list ){
        for(Map<String,Object> map : list){
            List<Performance> performanceList = performanceService.getListByRoleId((String) map.get("roleId"), DateUtils.parse(time, DateUtils.monthFormat));
            for(Performance performance : performanceList) {
                if(performance.getTypeId() == PerformanceTypeEnum.TRADING_VOLUME.getCode()) {
                    map.put("salesCountVolume",performance.getVolume());
                } else if (performance.getTypeId() == PerformanceTypeEnum.BUSINESS_VOLUME.getCode()) {
                    map.put("salesCommissionVolume",performance.getVolume());
                }
            }
        }
        return transferResult(list);
    }

    private List<Map<String,Object>> transferResult(List<Map<String,Object>> list ){
        for(Map<String,Object> map : list){
            if(null == map.get("roleId")){
                map.put("isLeafNode", true);
            }else{
                RoleTreeNode roleTreeNode = permissionService.getRoleTreeNodeByRoleId((String)map.get("roleId"));
                HintUtils.assertRes(roleTreeNode != null, "获取角色树异常");
                map.put("isLeafNode", roleTreeNode.isItemLeafNode());
            }
        }
        return list;
    }

    private Map<String, Object> initGetKpiByMonthConditionMap() {
        Map<String, Object> result = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(time)) {
            result.put("time", time);
        }
        if (StringUtils.isNotBlank(roleId)) {
            result.put("roleId", roleId);
        }
        return result;
    }

    private Map<String, Object> initGetKpiDetailsConditionMap(RoleTreeNode loginRoleTreeNode ) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(time)) {
            result.put("time", time);
        }
        //详情：获取子树ID
        if (loginRoleTreeNode != null) {
            List<RoleTreeNode> childList = loginRoleTreeNode.getChildList();
            List<String> groupIds = new ArrayList<String>();
            if (CollectionUtils.isNotEmpty(childList)) {
                for (RoleTreeNode treeNode : childList) {
                    groupIds.add(treeNode.getData().getRoleId());
                }
            }
            result.put("roleId", groupIds);
        }
        return result;
    }

    private Map<String,Object> initGetKpiLeafDetailConditionMap(RoleTreeNode loginRoleTreeNode ){
        Map<String, Object> result = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(time)) {
            result.put("time", time);
        }
        if(loginRoleTreeNode != null){
            List<RoleTreeNode>  childList = loginRoleTreeNode.getChildList();
            List<Integer> loginIds = new ArrayList<Integer>();
            if(CollectionUtils.isNotEmpty(childList)){
                for (RoleTreeNode treeNode : childList){
                    loginIds.addAll(treeNode.getData().getLoginIdList());
                }
            }
            result.put("loginId",loginIds);
        }
        return result;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }
}
