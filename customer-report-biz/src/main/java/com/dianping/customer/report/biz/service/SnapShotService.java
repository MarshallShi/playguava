package com.dianping.customer.report.biz.service;

import com.dianping.customer.report.biz.entity.SnapShot;

import java.util.List;

public interface SnapShotService {
    void save(SnapShot snapShot, int operatorId);

    void save(List<SnapShot> snapShotList, int operatorId);
}
