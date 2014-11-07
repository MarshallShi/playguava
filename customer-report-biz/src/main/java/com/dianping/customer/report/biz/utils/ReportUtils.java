package com.dianping.customer.report.biz.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.HashMap;

public class ReportUtils {
    public static final String BASE_JSON_PATH = "config/json/%s.json";

    public static HashMap<String, Object> getJsonMap(String fileName) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(fileName)) {
            try {
                String competitiveIntelligenceJsonStr = IOUtils.toString(new ClassPathResource(String.format(BASE_JSON_PATH, fileName), ReportUtils.class.getClassLoader()).getInputStream(), "UTF-8");
                result = JsonUtils.fromStr(competitiveIntelligenceJsonStr, HashMap.class);
            } catch (IOException e) {
            }
        }
        return result;
    }

    public static void main(String[] args) {
        HashMap<String, Object> result = ReportUtils.getJsonMap("CompetitiveIntelligenceByMonth");
        System.out.println("end");
    }
}
