package com.dianping.customer.report.web.action.report;

import com.dianping.customer.report.biz.annotation.AccessLogger;
import com.dianping.customer.report.biz.dto.Node;
import com.dianping.customer.report.biz.dto.RoleTreeNode;
import com.dianping.customer.report.biz.entity.Performance;
import com.dianping.customer.report.biz.enums.ApplicationExceptionEnum;
import com.dianping.customer.report.biz.exceptions.ApplicationException;
import com.dianping.customer.report.web.action.base.AjaxBase;
import com.dianping.customer.report.biz.service.PerformanceService;
import com.dianping.customer.report.biz.service.PermissionService;
import com.dianping.customer.report.biz.serviceagent.LogServiceAgent;
import com.dianping.customer.report.biz.utils.DateUtils;
import com.dianping.customer.report.biz.utils.JsonUtils;
import com.dianping.customer.report.biz.utils.LoginUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class PerformanceAction extends AjaxBase {
    private String data;
    private String roleId;

    @Autowired
    private PerformanceService performanceService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private LogServiceAgent logServiceAgent;

    @AccessLogger(reportName = "Performance.CurrentTeamPerformance")
    public String getCurrentTeamPerformance() {
        RoleTreeNode loginRoleTreeNode;
        if (StringUtils.isNotBlank(roleId)) {
            loginRoleTreeNode = permissionService.getRoleTreeNodeByRoleId(roleId);
        } else {
            loginRoleTreeNode = permissionService.getRoleTreeNodeByLoginId(LoginUtils.getLoginId());
        }
        List<Performance> performanceList = null;
        Map<String, RoleTreeNode> roleTreeNodeMap = new HashMap<String, RoleTreeNode>();

        if(loginRoleTreeNode != null) {
            performanceList = performanceService.getListByRoleId(loginRoleTreeNode.getData().getRoleId(), DateUtils.getNextMonth());
            roleTreeNodeMap.put(loginRoleTreeNode.getData().getRoleId(), loginRoleTreeNode);
        }
        setResponseData(performanceService.translateFromPerformanceList(performanceList, DateUtils.format(DateUtils.getNextMonth(), DateUtils.monthFormat), roleTreeNodeMap));
        return SUCCESS;
    }

    @AccessLogger(reportName = "Performance.SubTeamPerformance")
    public String getSubTeamPerformance() {
        RoleTreeNode loginRoleTreeNode;
        if (StringUtils.isNotBlank(roleId)) {
            loginRoleTreeNode = permissionService.getRoleTreeNodeByRoleId(roleId);
        } else {
            loginRoleTreeNode = permissionService.getRoleTreeNodeByLoginId(LoginUtils.getLoginId());
        }
        List<Performance> performanceList = null;
        List<String> roleIds = new ArrayList<String>();
        Map<String, RoleTreeNode> roleTreeNodeMap = new HashMap<String, RoleTreeNode>();

        if(loginRoleTreeNode != null && CollectionUtils.isNotEmpty(loginRoleTreeNode.getChildList()) && loginRoleTreeNode.isGroupLeader()) {
            for(RoleTreeNode child : loginRoleTreeNode.getChildList()) {
                roleIds.add(child.getData().getRoleId());
                roleTreeNodeMap.put(child.getData().getRoleId(), child);
            }
            performanceList = performanceService.getListByRoleIds(roleIds, DateUtils.getNextMonth());
        }

        setResponseData(performanceService.translateFromPerformanceList(performanceList, DateUtils.format(DateUtils.getNextMonth(), DateUtils.monthFormat), roleTreeNodeMap));
        return SUCCESS;
    }

    public String setPerformance() {
        try {
            List<Performance> performanceList = new ArrayList<Performance>();
            Map<String, Object> map = JsonUtils.fromStrToMap(data);
            List<Map> items = (List<Map>) map.get("items");
            if(CollectionUtils.isNotEmpty(items)) {
                for(Map itemMap : items) {
                    performanceList.add(new Performance((Integer) map.get("typeId"), (String) itemMap.get("roleId"), (Double.valueOf(itemMap.get("volume").toString()))));
                }
                performanceService.saveByTypeId(performanceList, (Integer) map.get("typeId"), DateUtils.parse((String) map.get("time"), DateUtils.monthFormat), LoginUtils.getLoginId());
            }
        } catch (Exception e) {
            logServiceAgent.exception(e);
            throw new ApplicationException(ApplicationExceptionEnum.OPERATION_FAILED, "指标设置失败");
        }
        setResponseData("指标设置成功!");
        return SUCCESS;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
