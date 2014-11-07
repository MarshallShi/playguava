package com.dianping.customer.report.biz.serviceagent;

import com.dianping.customer.report.biz.entity.PageResult;
import com.dianping.customer.report.biz.enums.ReportEnum;
import com.dianping.trade.data.dto.ReportQueryParameterDTO;
import com.dianping.trade.data.dto.ReportQueryResultDTO;
import com.dianping.trade.data.enums.ReportQueryOrderByType;

import java.util.Map;

public interface ReportServiceAgent {
    PageResult getReport(ReportEnum reportEnum, Map<String, Object> conditionMap);

    PageResult getReportByOrderAndPage(ReportEnum reportEnum, Map<String, Object> conditionMap, Map<ReportQueryOrderByType, Object> orderMap, Map<String, Object> pageMap);

    ReportQueryResultDTO getReport(ReportQueryParameterDTO reportQueryParameterDTO);
}
