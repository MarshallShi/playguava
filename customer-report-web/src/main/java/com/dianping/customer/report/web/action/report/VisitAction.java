package com.dianping.customer.report.web.action.report;

import com.dianping.customer.report.biz.annotation.AccessLogger;
import com.dianping.customer.report.biz.dto.RoleTreeNode;
import com.dianping.customer.report.biz.entity.PageResult;
import com.dianping.customer.report.biz.enums.ApplicationExceptionEnum;
import com.dianping.customer.report.biz.enums.ReportEnum;
import com.dianping.customer.report.biz.enums.TimeCondition;
import com.dianping.customer.report.biz.enums.VisitStatisticEnum;
import com.dianping.customer.report.biz.exceptions.ApplicationException;
import com.dianping.customer.report.biz.service.PermissionService;
import com.dianping.customer.report.biz.serviceagent.ReportServiceAgent;
import com.dianping.customer.report.biz.utils.DateUtils;
import com.dianping.customer.report.biz.utils.HintUtils;
import com.dianping.customer.report.biz.utils.LoginUtils;
import com.dianping.customer.report.web.action.base.ReportBase;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class VisitAction extends ReportBase {
    @Autowired
    private ReportServiceAgent reportServiceAgent;
    @Autowired
    private PermissionService permissionService;

    private Integer cityId;
    private String groupId;
    private Integer timeType;
    private Integer statisticType;
    private Integer userId;

    @AccessLogger(reportName = "VisitAction.Summary")
    public String getSummary() {
//        checkFeasibilityWhenGetSummary();
        ReportEnum reportEnum = getReportEnum();
        PageResult pageResult = reportServiceAgent.getReportByOrderAndPage(reportEnum, initConditionMap(), initOrderByMap(), initPageMap());

        if (statisticType != null && statisticType == VisitStatisticEnum.USER_DIMENSION.getCode()) {
            RoleTreeNode loginRoleTreeNode = permissionService.getRoleTreeNodeByLoginId(LoginUtils.getLoginId());
            if (loginRoleTreeNode != null) {
                List<Map<String, Object>> items = pageResult.getItems();
                for (Map<String, Object> item : items) {
                    item.put("showLink", permissionService.isChildNode(loginRoleTreeNode, Integer.parseInt(item.get("userId").toString())));
                }
            }
        }

        setResponseData(pageResult);
        return SUCCESS;
    }

    @AccessLogger(reportName = "VisitAction.Detail")
    public String getDetail() {
        setResponseData(reportServiceAgent.getReportByOrderAndPage(getReportDetailEnum(), initConditionMap(), initOrderByMap(), initPageMap()));
        return SUCCESS;
    }

    private void checkFeasibilityWhenGetSummary() {
        HintUtils.assertRes(timeType != null, "请选择时间段");
        HintUtils.assertRes(statisticType != null, "请选择统计维度");
    }

    private ReportEnum getReportEnum() {
        if (statisticType != null && timeType != null) {
            boolean byMonth = false;

            if (timeType == TimeCondition.CUR_MONTH.getCode() || timeType == TimeCondition.LAST_MONTH.getCode()) {
                byMonth = true;
            }

            if (statisticType == VisitStatisticEnum.USER_DIMENSION.getCode()) {
                return byMonth ? ReportEnum.VisitByUserMonth : ReportEnum.VisitByUserWeek;
            } else if (statisticType == VisitStatisticEnum.GROUP_DIMENSION.getCode()) {
                return byMonth ? ReportEnum.VisitByGroupMonth : ReportEnum.VisitByGroupWeek;
            } else if (statisticType == VisitStatisticEnum.CITY_DIMENSION.getCode()) {
                return byMonth ? ReportEnum.VisitByCityMonth : ReportEnum.VisitByCityWeek;
            }
        }
        throw new ApplicationException(ApplicationExceptionEnum.OPERATION_FAILED, "查询条件错误");
    }

    private ReportEnum getReportDetailEnum() {
        if (timeType == TimeCondition.CUR_MONTH.getCode() || timeType == TimeCondition.LAST_MONTH.getCode()) {
            return ReportEnum.VisitDetailByMonth;
        } else if (timeType == TimeCondition.CUR_WEEK.getCode() || timeType == TimeCondition.LAST_WEEK.getCode()) {
            return ReportEnum.VisitDetailByWeek;
        }
        throw new ApplicationException(ApplicationExceptionEnum.OPERATION_FAILED, "查询条件错误");
    }

    private Map<String, Object> initConditionMap() {
        Map<String, Object> result = new HashMap<String, Object>();
        if (cityId != null) {
            result.put("cityId", cityId);
        }
        if (StringUtils.isNotBlank(groupId)) {
            RoleTreeNode roleTreeNode = permissionService.getRoleTreeNodeByRoleId(groupId);
            if (roleTreeNode != null) {
                List<String> roleIdList = new ArrayList<String>();
                for (RoleTreeNode child : roleTreeNode.getChildList()) {
                    roleIdList.add(child.getData().getRoleId());
                }
                result.put("roleId", roleIdList);
            }
        }
        if (timeType != null) {
            result.put("time", getVisitTime(timeType));
        }
        if (userId != null) {
            result.put("saleId", userId);
        }
        return result;
    }

    private String getVisitTime(int timeType) {
        if (timeType == TimeCondition.CUR_MONTH.getCode()) {
            return DateUtils.format(DateUtils.getCurrentMonth(), DateUtils.monthFormat);
        } else if (timeType == TimeCondition.LAST_MONTH.getCode()) {
            return DateUtils.format(DateUtils.addMonth(DateUtils.getCurrentMonth(), -1), DateUtils.monthFormat);
        } else if (timeType == TimeCondition.CUR_WEEK.getCode()) {
            return DateUtils.format(DateUtils.addDays(new Date(), -1), DateUtils.simpleDateFormat);
        } else if (timeType == TimeCondition.LAST_WEEK.getCode()) {
            return DateUtils.format(DateUtils.addDays(new Date(), -7), DateUtils.simpleDateFormat);
        }
        throw new ApplicationException(ApplicationExceptionEnum.OPERATION_FAILED, "查询条件错误");
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }

    public void setStatisticType(Integer statisticType) {
        this.statisticType = statisticType;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}