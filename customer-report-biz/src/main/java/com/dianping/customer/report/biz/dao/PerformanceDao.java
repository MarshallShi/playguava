package com.dianping.customer.report.biz.dao;

import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.customer.report.biz.entity.Performance;

import java.util.Date;
import java.util.List;

public interface PerformanceDao extends BaseDao<Performance> {
    @DAOAction(action = DAOActionType.QUERY)
    List<Performance> getListByRoleIds(@DAOParam("roleIds") List<String> roleIds, @DAOParam("performanceTime") Date performanceTime);

    @DAOAction(action = DAOActionType.QUERY)
    List<Performance> getListByTypeIdAndRoleIds(@DAOParam("typeId") int typeId, @DAOParam("roleIds") List<String> roleIds, @DAOParam("performanceTime") Date performanceTime);

    @DAOAction(action = DAOActionType.DELETE)
    void removeByTypeIdAndRoleIds(@DAOParam("typeId") int typeId, @DAOParam("roleIds") List<String> roleIds, @DAOParam("performanceTime") Date performanceTime);
}
