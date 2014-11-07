package com.dianping.customer.report.biz.serviceagent.impl;

import com.dianping.customer.report.biz.entity.PageResult;
import com.dianping.customer.report.biz.entity.ReportConfigJson;
import com.dianping.customer.report.biz.enums.ReportEnum;
import com.dianping.customer.report.biz.service.ReportConfigJsonService;
import com.dianping.customer.report.biz.serviceagent.ReportServiceAgent;
import com.dianping.customer.report.biz.translator.ReportQueryParameterTranslator;
import com.dianping.customer.report.biz.translator.ReportQueryResultTranslator;
import com.dianping.customer.report.biz.utils.HintUtils;
import com.dianping.trade.data.api.ReportRemoteService;
import com.dianping.trade.data.dto.ReportQueryParameterDTO;
import com.dianping.trade.data.dto.ReportQueryResultDTO;
import com.dianping.trade.data.enums.ReportQueryOrderByType;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
public class ReportServiceAgentImpl implements ReportServiceAgent {
    @Autowired
    private ReportRemoteService reportRemoteService;
    @Autowired
    private ReportConfigJsonService reportConfigJsonService;

    @Override
    public PageResult getReport(ReportEnum reportEnum, Map<String, Object> conditionMap) {
        return getReportByOrderAndPage(reportEnum, conditionMap, null, null);
    }

    @Override
    public PageResult getReportByOrderAndPage(ReportEnum reportEnum, Map<String, Object> conditionMap, Map<ReportQueryOrderByType, Object> orderMap, Map<String, Object> pageMap) {
        PageResult result = new PageResult();
        ReportConfigJson reportConfigJson = reportConfigJsonService.getReportConfigJsonByTypeId(reportEnum.getTypeId());

        ReportQueryParameterDTO reportQueryParameterDTO = initCompetitiveIntelligenceQueryParameter(reportConfigJson, conditionMap, orderMap, pageMap);
        ReportQueryResultDTO reportQueryResultDTO = getReport(reportQueryParameterDTO);

        HintUtils.assertRes(reportQueryResultDTO.isResult(), reportQueryResultDTO.getMessage());

        result.setTotal(reportQueryResultDTO.getAllRecordCount());
        if(CollectionUtils.isNotEmpty(reportQueryResultDTO.getReport())) {
            result.setItems(ReportQueryResultTranslator.translateByReportEnum(reportQueryResultDTO.getReport(), reportConfigJson.getResult()));
        } else {
            result.setItems(new LinkedList());
        }
        return result;
    }

    private ReportQueryParameterDTO initCompetitiveIntelligenceQueryParameter(ReportConfigJson reportConfigJson, Map<String, Object> conditionMap, Map<ReportQueryOrderByType, Object> orderMap, Map<String, Object> pageMap) {
        ReportQueryParameterDTO result = new ReportQueryParameterDTO();
        result.setReportName(reportConfigJson.getReportName());
        result.setConditionList(ReportQueryParameterTranslator.toConditionDtoList(conditionMap, reportConfigJson.getCondition()));
        result.setReportQueryOrderByDTOList(ReportQueryParameterTranslator.toOrderByDtoList(orderMap, reportConfigJson.getResult()));
        result.setReportQueryPageDTO(ReportQueryParameterTranslator.toPageDto(pageMap));
        return result;
    }

    @Override
    public ReportQueryResultDTO getReport(ReportQueryParameterDTO reportQueryParameterDTO) {
        return reportRemoteService.getReport(reportQueryParameterDTO);
    }
}
