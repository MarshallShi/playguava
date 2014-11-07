package com.dianping.customer.report.biz.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dianping.customer.report.AbstractTestClass;
import com.dianping.customer.report.biz.entity.ReportAccessLog;

public class ReportAccessLogDaoTest extends AbstractTestClass {
    private static final String TEST_USER_NAME = "test";
	private static final int TEST_USER_ID = -1;
	private static final String TEST_REPORT_NAME = "testReport";
	@Autowired
    private ReportAccessLogDao reportAccessLogDao;

    private ReportAccessLog createReportAccessLog() {
    	ReportAccessLog ral = new ReportAccessLog();
    	ral.setAccessorId(TEST_USER_ID);
    	ral.setAccessorName(TEST_USER_NAME);
    	ral.setCreateBy(TEST_USER_ID);
    	ral.setCreateTime(new java.util.Date());
    	ral.setUpdateBy(TEST_USER_ID);
    	ral.setUpdateTime(new java.util.Date());
    	ral.setReportName(TEST_REPORT_NAME);
    	ral.setAccessDate(new java.sql.Date(new java.util.Date().getTime()));
    	ral.setId(reportAccessLogDao.insert(ral));
        return ral;
    }

    @Test
    public void should_success_when_insert() {
    	ReportAccessLog reportAccessLog = createReportAccessLog();
        Assert.assertTrue(reportAccessLog.getId() > 0);
    }
    
    @Test
    public void should_success_when_query() {
    	createReportAccessLog();
    	ReportAccessLog ral = reportAccessLogDao.findByAccessorIdAndDateAndReportName(TEST_USER_ID, new java.sql.Date(new java.util.Date().getTime()),TEST_REPORT_NAME);
        Assert.assertTrue(ral != null);
        Assert.assertTrue(ral.getReportName().equals(TEST_REPORT_NAME));
        Assert.assertTrue(ral.getAccessorName().equals(TEST_USER_NAME));
    }
}
