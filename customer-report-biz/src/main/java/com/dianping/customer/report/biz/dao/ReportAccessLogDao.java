package com.dianping.customer.report.biz.dao;

import java.sql.Date;

import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.customer.report.biz.entity.ReportAccessLog;

public interface ReportAccessLogDao extends BaseDao<ReportAccessLog>{

    @DAOAction(action = DAOActionType.INSERT)
    int insert(@DAOParam("entity") ReportAccessLog entity);
    
    @DAOAction(action = DAOActionType.LOAD)
    ReportAccessLog findByAccessorIdAndDateAndReportName(@DAOParam("accessorId") int accessorId,@DAOParam("accessDate") Date accessDate,@DAOParam("reportName") String reportName);
}
