package com.dianping.customer.report.web.struts;

import com.dianping.customer.report.biz.exceptions.OperationResult;
import com.opensymphony.xwork2.ActionInvocation;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONResult;

public class CompositeResult extends JSONResult {
    @Override
    public void execute(ActionInvocation actionInvocation) throws Exception {
        if(OperationResult.getInstance().isSuccess()){
            super.execute(actionInvocation);
        } else{
            Object rootObject = readRootObject(actionInvocation);
            ServletActionContext.getResponse().setStatus(500);
            ServletActionContext.getResponse().setContentType("text/html");
            ServletActionContext.getResponse().getWriter().write((String)rootObject);
        }
    }
}
