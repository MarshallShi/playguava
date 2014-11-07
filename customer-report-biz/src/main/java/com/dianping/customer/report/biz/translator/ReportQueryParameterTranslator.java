package com.dianping.customer.report.biz.translator;

import com.dianping.customer.report.biz.enums.PageTypeEnum;
import com.dianping.customer.report.biz.utils.StringUtils;
import com.dianping.trade.data.dto.ReportQueryConditionDTO;
import com.dianping.trade.data.dto.ReportQueryOrderByDTO;
import com.dianping.trade.data.dto.ReportQueryPageDTO;
import com.dianping.trade.data.enums.ReportQueryOrderByType;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ReportQueryParameterTranslator {
    public static List<ReportQueryConditionDTO> toConditionDtoList(Map<String, Object> conditionMap, Map<String, String> conditionConfigMap) {
        List<ReportQueryConditionDTO> result = null;
        if(conditionMap != null && !conditionMap.isEmpty()) {
            result = new LinkedList<ReportQueryConditionDTO>();
            for(String key : conditionConfigMap.keySet()) {
                if(conditionMap.containsKey(key)) {
                    result.add(initConditionDto(conditionConfigMap.get(key), conditionMap.get(key)));
                }
            }
        }
        return result;
    }

    public static List<ReportQueryOrderByDTO> toOrderByDtoList(Map<ReportQueryOrderByType, Object> orderMap, Map<String, String> orderConfigMap) {
        List<ReportQueryOrderByDTO> result = null;
        if(orderMap != null && !orderMap.isEmpty()) {
            result = new LinkedList<ReportQueryOrderByDTO>();
            for(ReportQueryOrderByType key : orderMap.keySet()) {
                String value = (String) orderMap.get(key);
                if(StringUtils.isNotBlank(value)) {
                    for(String orderBy : value.split(",")) {
                        result.add(initOrderByDto(orderConfigMap.get(orderBy), key));
                    }
                }
            }
        }
        return result;
    }

    public static ReportQueryPageDTO toPageDto(Map<String, Object> pageMap) {
        ReportQueryPageDTO result = null;
        if(pageMap != null && !pageMap.isEmpty()) {
            result = new ReportQueryPageDTO();
            result.setPage((Integer) pageMap.get(PageTypeEnum.PAGE_INDEX.getDesc()));
            result.setSize((Integer) pageMap.get(PageTypeEnum.PAGE_SIZE.getDesc()));
        }
        return result;
    }

    private static ReportQueryConditionDTO initConditionDto(String key, Object value) {
        ReportQueryConditionDTO result = new ReportQueryConditionDTO();
        result.setKey(key);
        result.setValue(value);
        return result;
    }

    private static ReportQueryOrderByDTO initOrderByDto(String name, ReportQueryOrderByType type) {
        ReportQueryOrderByDTO result = new ReportQueryOrderByDTO();
        result.setName(name);
        result.setType(type);
        return result;
    }
}
