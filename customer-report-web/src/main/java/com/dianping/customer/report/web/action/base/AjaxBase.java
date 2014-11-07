package com.dianping.customer.report.web.action.base;

import com.dianping.customer.report.biz.exceptions.OperationResult;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AjaxBase {
    public static String SUCCESS = "success";
    public static String ERROR = "error";

    private Object responseData;

    public Object getResult() {
        if (!OperationResult.getInstance().isSuccess()) {
            this.responseData=OperationResult.getInstance().getFormatedErrorMessages();
        }
        return responseData;
    }

    public void setFailureResult(String msg) {
        OperationResult.getInstance().addErrorMessage(ERROR, null, msg);
    }


    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }

    protected void setPageData(Collection resultList) {
        Map<String, Object> resultPage = new HashMap<String, Object>();
        resultPage.put("total", resultList.size());
        resultPage.put("items", resultList);
        this.responseData = resultPage;
    }

}
