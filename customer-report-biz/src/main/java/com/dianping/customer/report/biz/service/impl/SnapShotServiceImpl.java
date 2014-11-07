package com.dianping.customer.report.biz.service.impl;

import com.dianping.customer.report.biz.dao.SnapShotDao;
import com.dianping.customer.report.biz.entity.SnapShot;
import com.dianping.customer.report.biz.service.SnapShotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SnapShotServiceImpl implements SnapShotService {
    @Autowired
    private SnapShotDao snapShotDao;

    @Override
    public void save(SnapShot snapShot, int operatorId) {
        snapShot.setCreateTime(new Date());
        snapShot.setCreateBy(operatorId);
        snapShot.setId(snapShotDao.insert(snapShot));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(List<SnapShot> snapShotList, int operatorId) {
        Date createTime = new Date();
        for(SnapShot snapShot : snapShotList) {
            snapShot.setCreateTime(createTime);
            snapShot.setCreateBy(operatorId);
            snapShot.setId(snapShotDao.insert(snapShot));
        }
    }
}
