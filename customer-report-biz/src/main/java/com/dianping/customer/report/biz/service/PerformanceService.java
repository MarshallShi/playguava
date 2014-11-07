package com.dianping.customer.report.biz.service;

import com.dianping.customer.report.biz.dto.Node;
import com.dianping.customer.report.biz.dto.RoleTreeNode;
import com.dianping.customer.report.biz.entity.Performance;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PerformanceService {
    void save(Performance performance, int operatorId);

    void saveByTypeId(List<Performance> performanceList, int typeId, Date performanceTime, int operatorId) throws IOException;

    List<Performance> getListByRoleId(String roleId, Date performanceTime);

    List<Performance> getListByRoleIds(List<String> roleIds, Date performanceTime);

    List<Performance> getListByTypeIdAndRoleIds(int typeId, List<String> roleIds, Date performanceTime);

    Performance insert(String roleId, int typeId, double volume, Date performanceTime, int operatorId);

    List<Map> translateFromPerformanceList(List<Performance> performanceList, String time, Map<String, RoleTreeNode> roleTreeNodeMap);
}
