package com.dianping.customer.report.biz.service.impl;

import com.dianping.ba.base.organizationalstructure.api.user.dto.UserDto;
import com.dianping.customer.report.biz.dao.PerformanceDao;
import com.dianping.customer.report.biz.dto.Node;
import com.dianping.customer.report.biz.dto.RoleTreeNode;
import com.dianping.customer.report.biz.entity.Performance;
import com.dianping.customer.report.biz.entity.SnapShot;
import com.dianping.customer.report.biz.enums.PerformanceTypeEnum;
import com.dianping.customer.report.biz.service.PerformanceService;
import com.dianping.customer.report.biz.service.PermissionService;
import com.dianping.customer.report.biz.service.SnapShotService;
import com.dianping.customer.report.biz.serviceagent.UserServiceAgent;
import com.dianping.customer.report.biz.utils.CollectionUtils;
import com.dianping.customer.report.biz.utils.DateUtils;
import com.dianping.customer.report.biz.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

@Service
public class PerformanceServiceImpl implements PerformanceService {
    @Autowired
    private PerformanceDao performanceDao;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private UserServiceAgent userServiceAgent;
    @Autowired
    private SnapShotService snapShotService;

    private static final String SNAP_SHOT_KEY = "%s_%s_%s";

    @Override
    public void save(Performance performance, int operatorId) {
        performance.setUpdateTime(new Date());
        performance.setUpdateBy(operatorId);
        if(performance.getId() > 0) {
            performanceDao.update(performance);
        } else {
            performance.setCreateTime(new Date());
            performance.setCreateBy(operatorId);
            performance.setId(performanceDao.insert(performance));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveByTypeId(List<Performance> performanceList, int typeId, Date performanceTime, int operatorId) throws IOException {
        List<String> roleIds = new ArrayList<String>();
        if(CollectionUtils.isNotEmpty(performanceList)) {
            List<SnapShot> snapShotList = new ArrayList<SnapShot>();
            for(Performance performance : performanceList) {
                roleIds.add(performance.getRoleId());
            }

            performanceDao.removeByTypeIdAndRoleIds(typeId, roleIds, performanceTime);

            for(Performance performance : performanceList) {
                performance.setPerformanceTime(performanceTime);
                performance.setTypeId(typeId);
                performance.setId(0);
                save(performance, operatorId);
                snapShotList.add(new SnapShot(String.format(SNAP_SHOT_KEY, performance.getRoleId(), performance.getTypeId(), DateUtils.format(performance.getPerformanceTime(), DateUtils.monthFormat)), JsonUtils.toStr(performance), operatorId));
            }

            snapShotService.save(snapShotList, operatorId);
        }
    }

    @Override
    public List<Performance> getListByRoleId(String roleId, Date performanceTime) {
        List<String> roleIds = new ArrayList<String>();
        roleIds.add(roleId);
        return getListByRoleIds(roleIds, performanceTime);
    }

    @Override
    public List<Performance> getListByRoleIds(List<String> roleIds, Date performanceTime) {
        return performanceDao.getListByRoleIds(roleIds, performanceTime);
    }

    @Override
    public List<Performance> getListByTypeIdAndRoleIds(int typeId, List<String> roleIds, Date performanceTime) {
        return performanceDao.getListByTypeIdAndRoleIds(typeId, roleIds, performanceTime);
    }

    @Override
    public Performance insert(String roleId, int typeId, double volume, Date performanceTime, int operatorId) {
        Performance performance = new Performance();
        performance.setRoleId(roleId);
        performance.setTypeId(typeId);
        performance.setVolume(volume);
        performance.setPerformanceTime(performanceTime);
        save(performance, operatorId);
        return performance;
    }

    @Override
    public List<Map> translateFromPerformanceList(List<Performance> performanceList, String time, Map<String, RoleTreeNode> roleTreeNodeMap) {
        List<Map> result = new ArrayList<Map>();
        Map<Integer, List> performanceTypeMap = new HashMap<Integer, List>();
        for(PerformanceTypeEnum performanceTypeEnum : PerformanceTypeEnum.values()) {
            Map<String, Object> itemMap = new HashMap<String, Object>();
            itemMap.put("typeId", performanceTypeEnum.getCode());
            itemMap.put("typeName", performanceTypeEnum.getDesc());
            itemMap.put("time", time);
            List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
            itemMap.put("items", itemList);
            result.add(itemMap);
            performanceTypeMap.put(performanceTypeEnum.getCode(), itemList);
        }

        if(CollectionUtils.isNotEmpty(performanceList)) {
            for(Performance performance : performanceList) {
                performanceTypeMap.get(performance.getTypeId()).add(translateFromPerformance(performance));
            }
        }

        if(roleTreeNodeMap != null && !roleTreeNodeMap.isEmpty()) {
            extendPerformanceTypeMap(performanceTypeMap, roleTreeNodeMap);
        }
        return result;
    }

    public Map<String, Object> translateFromPerformance(Performance performance) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("roleId", performance.getRoleId());
        result.put("operatorId", performance.getUpdateBy());
        result.put("typeId", performance.getTypeId());
        result.put("typeName", PerformanceTypeEnum.getByCode(performance.getTypeId()).getDesc());
        result.put("time", DateUtils.format(performance.getPerformanceTime(), DateUtils.monthFormat));
        result.put("volume", performance.getVolume());

        RoleTreeNode loginRoleTreeNode = permissionService.getRoleTreeNodeByRoleId(performance.getRoleId());
        if(loginRoleTreeNode != null) {
            result.put("roleName", loginRoleTreeNode.getData().getRoleName());
            result.put("canDrill", CollectionUtils.isEmpty(loginRoleTreeNode.getData().getLoginIdList()) && loginRoleTreeNode.isGroupLeader());
        }

        UserDto operator = userServiceAgent.queryUserByLoginID(performance.getUpdateBy());
        if(operator != null) {
            result.put("operatorName", operator.getRealName());
        }
        return result;
    }

    private void extendPerformanceTypeMap(Map<Integer, List> performanceTypeMap, Map<String, RoleTreeNode> roleTreeNodeMap) {
        if(roleTreeNodeMap != null && !roleTreeNodeMap.isEmpty() && performanceTypeMap != null && !performanceTypeMap.isEmpty()) {
            for(String nodeKey : roleTreeNodeMap.keySet()) {
                for(int performanceMapKey : performanceTypeMap.keySet()) {
                    boolean hasInclude = false;
                    List<Map<String, Object>> performanceItemList = performanceTypeMap.get(performanceMapKey);
                    if(CollectionUtils.isNotEmpty(performanceItemList)) {
                        for(Map<String, Object> itemMap : performanceItemList) {
                            if(itemMap.get("roleId").equals(nodeKey)) {
                                hasInclude = true;
                                break;
                            }
                        }
                    }
                    if(!hasInclude) {
                        performanceItemList.add(initDefaultPerformanceMap(performanceMapKey, roleTreeNodeMap.get(nodeKey)));
                    }
                }
            }
        }
    }

    private Map<String, Object> initDefaultPerformanceMap(int performanceTypeId, RoleTreeNode roleTreeNode) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("roleId", roleTreeNode.getData().getRoleId());
        result.put("roleName", roleTreeNode.getData().getRoleName());
        result.put("typeId", performanceTypeId);
        result.put("typeName", PerformanceTypeEnum.getByCode(performanceTypeId).getDesc());
        result.put("canDrill", CollectionUtils.isNotEmpty(roleTreeNode.getData().getLoginIdList()) && roleTreeNode.isGroupLeader());
        return result;
    }
}
