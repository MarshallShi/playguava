package com.dianping.customer.report.biz.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.customer.report.biz.entity.SnapShot;

public interface SnapShotDao extends GenericDao {
    @DAOAction(action = DAOActionType.INSERT)
    int insert(@DAOParam("entity") SnapShot entity);
}
