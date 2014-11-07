package com.dianping.customer.report.biz.utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static int getCountSplitByComma(String str) {
        if (isBlank(str)) return 0;
        return str.split(",").length;
    }

    public static boolean isBlank(String content) {
        return org.apache.commons.lang.StringUtils.isBlank(content);
    }

    public static boolean isNotBlank(String content) {
        return org.apache.commons.lang.StringUtils.isNotBlank(content);
    }

    public static boolean isNumeric(Object obj) {
        if (obj == null || isBlank(obj.toString()))
            return false;

        return org.apache.commons.lang.StringUtils.isNumeric(obj.toString());
    }

    public static String valueTrim(String str) {
        if (isBlank(str))
            return null;
        return str.trim();
    }

    public static String valueOf(Object object) {
        if (object == null)
            return "";

        return object.toString();
    }

    public static String join(Collection collection, String separator) {
        return org.apache.commons.lang.StringUtils.join(collection, separator);
    }

    public static String clearHtmlAndScript(String content) {

        if (StringUtils.isBlank(content)) {
            return content;
        }

        Pattern pattern = Pattern.compile("<script[^>]*?>.*?</script>|<(.[^>]*)>|[\\r\\n]|<!--.*|-->");
        Matcher matcher = pattern.matcher(content);

        if (matcher.find())
            content = matcher.replaceAll("");

        pattern = Pattern.compile("&(quot|#34);");
        matcher = pattern.matcher(content);

        if (matcher.find())
            content = matcher.replaceAll("\"");

        pattern = Pattern.compile("&(amp|#38);");
        matcher = pattern.matcher(content);

        if (matcher.find())
            content = matcher.replaceAll("&");

        pattern = Pattern.compile("&(lt|#60);");
        matcher = pattern.matcher(content);

        if (matcher.find())
            content = matcher.replaceAll("<");

        pattern = Pattern.compile("&(gt|#62);");
        matcher = pattern.matcher(content);

        if (matcher.find())
            content = matcher.replaceAll(">");

        pattern = Pattern.compile("&(nbsp|#160);");
        matcher = pattern.matcher(content);

        if (matcher.find())
            content = matcher.replaceAll(" ");

        pattern = Pattern.compile("&(iexcl|#161);");
        matcher = pattern.matcher(content);

        if (matcher.find())
            content = matcher.replaceAll("\\\\xa1");

        pattern = Pattern.compile("&(cent|#162);");
        matcher = pattern.matcher(content);

        if (matcher.find())
            content = matcher.replaceAll("\\\\xa2");

        pattern = Pattern.compile("&(pound|#163);");
        matcher = pattern.matcher(content);

        if (matcher.find())
            content = matcher.replaceAll("\\\\xa3");

        pattern = Pattern.compile("&(copy|#169);");
        matcher = pattern.matcher(content);

        if (matcher.find())
            content = matcher.replaceAll("\\\\xa9");

        pattern = Pattern.compile("&#(\\d+);");
        matcher = pattern.matcher(content);

        if (matcher.find())
            content = matcher.replaceAll("");

        return content;
    }

    public static Set<Integer> convertStringToHashSet(String str) {
        Set<Integer> integers = new HashSet<Integer>();
        if (StringUtils.isNotBlank(str)) {
            String[] array = str.split(",");
            List<String> strings = Arrays.asList(array);
            for (String s : strings) {
                integers.add(Integer.parseInt(s.trim()));
            }
        }
        return integers;
    }
}
