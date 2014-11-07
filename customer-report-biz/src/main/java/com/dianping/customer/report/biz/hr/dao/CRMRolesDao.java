package com.dianping.customer.report.biz.hr.dao;

/**
 * Created by zaza on 14-10-16.
 */

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.customer.report.biz.entity.CRMRoles;

import java.util.List;

public interface CRMRolesDao extends GenericDao {
    @DAOAction(action = DAOActionType.QUERY)
    List<CRMRoles> getCRMRolesByRoleId(@DAOParam("roleId") String roleId);

    @DAOAction(action = DAOActionType.QUERY)
    List<CRMRoles> getAllCRMRoles();

    @DAOAction(action = DAOActionType.LOAD)
    CRMRoles getRootNode();
}
