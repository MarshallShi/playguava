package com.dianping.customer.report.biz.hr.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.customer.report.biz.entity.CRMRoleMapping;

import java.util.List;

/**
 * Created by zaza on 14-10-16.
 */
public interface CRMRoleMappingDao extends GenericDao {
    @DAOAction(action = DAOActionType.QUERY)
    List<CRMRoleMapping> getCRMRoleMappingByLoginId(@DAOParam("loginId") int loginId);

}
