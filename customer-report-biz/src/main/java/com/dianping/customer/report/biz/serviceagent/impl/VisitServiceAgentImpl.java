package com.dianping.customer.report.biz.serviceagent.impl;

import com.dianping.customer.report.biz.serviceagent.LogServiceAgent;
import com.dianping.customer.report.biz.serviceagent.VisitServiceAgent;
import com.dianping.customerinfo.api.VisitService;
import com.dianping.customerinfo.constants.CoopTypeEnum;
import com.dianping.customerinfo.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shenyoujun on 14-9-25.
 */
@Component
public class VisitServiceAgentImpl implements VisitServiceAgent {
    @Autowired
    private LogServiceAgent logServiceAgent;
    @Autowired
    private VisitService visitService;

    private static final int ZERO =0;

    public Tuple<Integer, List<Visit>> queryVisitByShopID(int shopID, int pageSize, int pageIndex) {
        try{
            return visitService.queryVisitByShopID(shopID,pageSize,pageIndex);
        }catch (Exception e){
            logServiceAgent.exception("VisitServiceAgentImpl.queryVisitByShopID", "通过分店获取拜访失败", e);
            return new Tuple<Integer, List<Visit>>(0,new ArrayList<Visit>());
        }
    }

    @Override
    public List<CoopStatusDTO> getShopCoopStatus(List<Integer> shopIds) {
        try{
            OperationResult<List<CoopStatusDTO>> result =
                    visitService.getShopCoopStatus(shopIds, CoopTypeEnum.GROUP.getCode());
            if(result.isSuccess()) return result.getObj();

            return new ArrayList<CoopStatusDTO>();
        }catch (Exception e){
            logServiceAgent.exception("VisitServiceAgentImpl.queryVisitByShopID", "获取分店合作状态失败", e);
            return new ArrayList<CoopStatusDTO>();
        }
    }

    @Override
    public Tuple<Integer, List<VisitQueryDTO>> getMyVisitsByCondition(Date beginDate, Date endDate, Integer hasPartner, Integer userId, Integer contactType,Integer shopCooperatorStatus, int pageSize, int pageIndex) {
        try{
            return visitService.getMyVisitsByCondition(beginDate,endDate,hasPartner, userId,contactType,shopCooperatorStatus,pageSize,pageIndex);
        }catch (Exception e){
            logServiceAgent.exception("VisitServiceAgentImpl.queryVisitByShopID", "获取拜访列表失败", e);
            return new Tuple<Integer, List<VisitQueryDTO>>(ZERO,new ArrayList<VisitQueryDTO>());
        }
    }

    public void syncShopStatus() {
        visitService.syncShopCoop();
    }
}
