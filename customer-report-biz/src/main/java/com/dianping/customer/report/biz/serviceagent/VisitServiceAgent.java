package com.dianping.customer.report.biz.serviceagent;

import com.dianping.customerinfo.dto.CoopStatusDTO;
import com.dianping.customerinfo.dto.Tuple;
import com.dianping.customerinfo.dto.Visit;
import com.dianping.customerinfo.dto.VisitQueryDTO;

import java.util.Date;
import java.util.List;

/**
 * Created by shenyoujun on 14-9-25.
 */
public interface VisitServiceAgent {
    Tuple<Integer, List<Visit>> queryVisitByShopID(int shopID,int pageSize,int pageIndex);

    List<CoopStatusDTO> getShopCoopStatus(List<Integer> shopIds);

    Tuple<Integer,List<VisitQueryDTO>> getMyVisitsByCondition(Date beginDate,Date endDate , Integer hasPartner, Integer userId,
            Integer contactType,Integer shopCooperatorStatus,int pageSize,int pageIndex);

    public void syncShopStatus();
}
