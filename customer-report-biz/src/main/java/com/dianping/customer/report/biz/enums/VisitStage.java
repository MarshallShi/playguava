package com.dianping.customer.report.biz.enums;

import com.dianping.customer.report.biz.serviceagent.UserGroupServiceAgent;
import com.dianping.customer.report.biz.utils.Beans;
import com.dianping.customer.report.biz.utils.CollectionUtils;
import com.dianping.customer.report.biz.utils.ConfigUtils;
import com.google.common.base.Predicate;

import java.util.*;

public enum VisitStage {
    REQUIREMENT_COMMUNICATION ( 1,"需求沟通"),
    INTRODUCE_PRODUCT ( 2,"介绍产品"),
    NEGOTIATE_PRICE ( 3,"协商价格"),
    SIGN_CONTRACT ( 4,"签订合同"),
    AFTERMARKET_SERVICE ( 5,"售后维护"),
    NEGOTIATION(6,"谈判签单"),
    SUSPEND(7,"暂无合作意向") ;

    private int code;
    private String desc;

    VisitStage(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private static final String CODE = "value";
    private static final String DESC = "text";

    public static VisitStage getByCode(int code) {
        for (VisitStage enumItem : values())
            if (enumItem.getCode() == code)
                return enumItem;
        return null;
    }

    private static void addToList(List<Map<String,Object>> resultList,VisitStage visitStage){
        Map<String,Object> enumInfo = new HashMap<String, Object>();
        enumInfo.put(CODE,visitStage.code);
        enumInfo.put(DESC,visitStage.desc);
        resultList.add(enumInfo);
    }

    public static List<Map<String,Object>> toList(){
        List<Map<String,Object>> resultList = new ArrayList<Map<String, Object>>();
        for(VisitStage enumItem: values()){
            addToList(resultList,enumItem);
        }
        return resultList;
    }



//    public List<Integer> getType() {
//        List<Integer> result = Lists.newArrayList();
//        if (code == 1 || code == 5|| code == 6 || code == 7)
//            result.add(BusinessUnitTypeEnum.BU_JIAOYI.getCode());
//        if (code == 1 || code == 2 || code == 3 || code == 4 || code == 5)
//            result.add(BusinessUnitTypeEnum.BU_TUIGUANG.getCode());
//
//        return result;
//    }

}
