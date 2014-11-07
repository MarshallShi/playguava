package com.dianping.customer.report.biz.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.customer.report.biz.entity.BaseEntity;

public interface BaseDao<T extends BaseEntity> extends GenericDao {
    @DAOAction(action = DAOActionType.LOAD)
    T findById(@DAOParam("id") int id);

    @DAOAction(action = DAOActionType.INSERT)
    int insert(@DAOParam("entity") T entity);

    @DAOAction(action = DAOActionType.DELETE)
    void remove(@DAOParam("entity") T entity);

    @DAOAction(action = DAOActionType.UPDATE)
    int update(@DAOParam("entity") T entity);
}
