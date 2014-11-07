package com.dianping.customer.report.biz.dao;

import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.customer.report.biz.entity.ReportConfigJson;

public interface ReportConfigJsonDao extends BaseDao<ReportConfigJson> {
    @DAOAction(action = DAOActionType.LOAD)
    ReportConfigJson findByTypeId(@DAOParam("typeId") int typeId);
}
