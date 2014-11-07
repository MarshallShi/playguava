package com.dianping.customer.report.biz.utils;

import com.dianping.customer.report.biz.enums.ApplicationExceptionEnum;
import com.dianping.customer.report.biz.exceptions.ApplicationException;

import java.util.Map;

public class HintUtils {
    public static String WITH_NO_VALID_WORKFLOW = "团单无对应工作流程,操作失败,请重新创建";

    public static void assertRes(boolean res,String hint)
    {
        if(!res)
            throw new ApplicationException( ApplicationExceptionEnum.OPERATION_FAILED,hint);

    }

    public static void hint(Map<String, String> errorDetail, String source){
        throw new ApplicationException(ApplicationExceptionEnum.OPERATION_FAILED,getErrorMsg(errorDetail) + "{{[]}} " +source);
    }

    private static String getErrorMsg(Map<String, String> checkResult) {
        StringBuilder sb = new StringBuilder();
        for(String key:checkResult.keySet())
            sb.append(checkResult.get(key)+"\n");
        return sb.toString();
    }

    public static void assertRes(boolean res, Map<String, String> checkResult) {
        assertRes(res,getErrorMsg(checkResult));
    }
}
