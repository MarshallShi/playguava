package com.dianping.customer.report.biz.service.impl;

import com.dianping.customer.report.biz.dao.ReportConfigJsonDao;
import com.dianping.customer.report.biz.entity.ReportConfigJson;
import com.dianping.customer.report.biz.service.ReportConfigJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportConfigJsonServiceImpl implements ReportConfigJsonService {
    @Autowired
    private ReportConfigJsonDao reportConfigJsonDao;

    @Override
    public ReportConfigJson getReportConfigJsonByTypeId(int typeId) {
        return reportConfigJsonDao.findByTypeId(typeId);
    }
}
