package com.dianping.customer.report.biz.service;

import com.dianping.customer.report.biz.entity.PageFrameSet;

import java.util.List;

/**
 * Created by gelin on 14-10-17.
 */
public interface PageFrameSetService {
    List<PageFrameSet> getPageFrameSetList(int loginId);
}
