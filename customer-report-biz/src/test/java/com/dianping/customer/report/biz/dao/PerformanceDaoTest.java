package com.dianping.customer.report.biz.dao;

import com.dianping.customer.report.AbstractTestClass;
import com.dianping.customer.report.biz.entity.Performance;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class PerformanceDaoTest extends AbstractTestClass {
    @Autowired
    private PerformanceDao performanceDao;

    private Performance initPerformanceIndex(String roleId, int typeId) {
        Performance performance = new Performance();
        
        performance.setRoleId(roleId);
        performance.setTypeId(typeId);
        performance.setPerformanceTime(new Date());
        performance.setCreateBy(1);
        performance.setCreateTime(new Date());
        performance.setUpdateBy(1);
        performance.setUpdateTime(new Date());
        performance.setId(performanceDao.insert(performance));
        return performance;
    }

    @Test
    public void should_success_when_insert() {
        Performance performance = initPerformanceIndex("1", 1);
        Assert.assertTrue(performance.getId() > 0);
    }
}
