package com.dianping.customer.report.web.translator;

import com.dianping.combiz.entity.City;
import com.dianping.combiz.entity.Region;
import com.dianping.customer.report.biz.dto.Node;
import com.dianping.customer.report.biz.dto.RoleTreeNode;
import com.dianping.customer.report.biz.utils.DateUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

public class CommonDataTranslator {
    public static final String MONTH = "%s月";

    public static List<Map<String, Object>> fromRoleTreeNodeByParent(RoleTreeNode roleTreeNode) {
        LinkedList<Map<String, Object>> result = new LinkedList<Map<String, Object>>();
        RoleTreeNode currentNode = roleTreeNode;
        while (currentNode != null) {
            result.addFirst(getDropDownMap(currentNode.getData().getRoleName(), currentNode.getData().getRoleId()));
            currentNode = currentNode.getParent();
        }
        return result;
    }

    public static List<Map<String, Object>> fromRoleTreeNodeByChildren(RoleTreeNode roleTreeNode) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        List<RoleTreeNode> childrenList = roleTreeNode.getChildList();
        if(CollectionUtils.isNotEmpty(childrenList)) {
            Collections.sort(childrenList, new Comparator<RoleTreeNode>() {
                @Override
                public int compare(RoleTreeNode node1, RoleTreeNode node2) {
                    return node1.getData().getRoleName().compareTo(node2.getData().getRoleName());
                }
            });

            for (RoleTreeNode child : childrenList) {
                result.add(getDropDownMap(child.getData().getRoleName(), child.getData().getRoleId()));
            }
        }
        return result;
    }

    public static List<Map<String, Object>> fromCityList(List<City> cityList) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if (CollectionUtils.isNotEmpty(cityList)) {
            for (City city : cityList) {
                Map<String, Object> cityMap = new HashMap<String, Object>();
                cityMap.put("value", city.getCityID());
                cityMap.put("text", city.getCityName());
                cityMap.put("pinyin", city.getCityEnName().toLowerCase());
                result.add(cityMap);
            }
        }
        return result;
    }

    public static List<Map<String, Object>> fromRegionList(List<Region> regionList) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if (CollectionUtils.isNotEmpty(regionList)) {
            for (Region region : regionList) {
                result.add(getDropDownMap(region.getRegionName(), region.getRegionId()));
            }
        }
        return result;
    }

    public static List<Map<String, Object>> fromCategoryList(List<Map<String, Object>> categoryList) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if (CollectionUtils.isNotEmpty(categoryList)) {
            for (Map<String, Object> category : categoryList) {
                result.add(getDropDownMap(category.get("categoryName").toString(), category.get("categoryName")));
            }
        }
        return result;
    }

    public static List<Map<String, Object>> fromMonthOfYear(List<Date> monthOfYear) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if (CollectionUtils.isNotEmpty(monthOfYear)) {
            for (Date month : monthOfYear) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(month);
                result.add(getDropDownMap(String.format(MONTH, cal.get(Calendar.MONTH) + 1), DateUtils.format(month, DateUtils.monthFormat)));
            }
        }
        return result;
    }

    public static List<Map<String,Object>> fromGroupByCityId(List<RoleTreeNode> groups){
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if(CollectionUtils.isNotEmpty(groups)){
            for (RoleTreeNode treeNode : groups){
                Node treeNodeData = treeNode.getData();
                if(treeNodeData != null){
                    result.add(getDropDownMap(treeNodeData.getRoleName(),treeNodeData.getRoleId()));
                }
            }
        }
        return result;
    }

    private static Map<String, Object> getDropDownMap(String text, Object value) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("text", text);
        result.put("value", value);
        return result;
    }
}
