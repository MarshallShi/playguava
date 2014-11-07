package com.dianping.customer.report.biz.translator;

import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ReportQueryResultTranslator {
    public static List<Map<String, Object>> translateByReportEnum(List<HashMap<String, Object>> reportQueryResultList, Map<String, String> resultConfigMap) {
        List<Map<String, Object>> result = new LinkedList<Map<String, Object>>();
        if(CollectionUtils.isNotEmpty(reportQueryResultList)) {
            for(Map<String, Object> reportQueryResult : reportQueryResultList) {
                Map<String, Object> reportResult = new HashMap<String, Object>();
                result.add(reportResult);
                for(String key : resultConfigMap.keySet()) {
                    reportResult.put(key, reportQueryResult.get(resultConfigMap.get(key)));
                }
            }
        }
        return result;
    }
}
