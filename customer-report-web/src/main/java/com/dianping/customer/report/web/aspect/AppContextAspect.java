package com.dianping.customer.report.web.aspect;

import com.dianping.customer.report.biz.exceptions.ApplicationException;
import com.dianping.customer.report.biz.exceptions.OperationResult;
import com.dianping.customer.report.web.action.base.AjaxBase;
import com.dianping.customer.report.biz.serviceagent.LogServiceAgent;
import com.dianping.customer.report.biz.utils.ExceptionUtils;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@Component
@Aspect
public class AppContextAspect {

    @Autowired
    private LogServiceAgent logServiceAgent;

    @Around("execution( public String com.dianping.customer.report.web.action.*.*.*())")
    public Object appContextHandling(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            joinPoint.proceed();
        } catch (Throwable ex) {
            handleException(ex);
        }
        return AjaxBase.SUCCESS;
    }

    private void handleException(Throwable ex) {

        while (ex instanceof InvocationTargetException) {
            ex = ((InvocationTargetException) ex).getTargetException();
        }
        if (ex instanceof ApplicationException) {
            ApplicationException applicationException = (ApplicationException) ex;
            OperationResult.getInstance().addErrorMessage(applicationException.getApplicationExceptionEnum().name(), Lists.transform(Arrays.asList(applicationException.getArgs()), new Function<Object, String>() {
                @Override
                public String apply(Object input) {
                    if (input == null)
                        return "";
                    return input.toString();
                }
            }), "系统异常，原因未知，请联系开发人员");
            return;
        }

        logServiceAgent.exception(ex);
//        if("product".equals(EnvZooKeeperConfig.getEnv())){
//            OperationResult.getInstance().addErrorMessage("unknown.error","系统异常，原因未知，请联系开发人员");
//        } else{
        OperationResult.getInstance().addErrorMessage("unknown.error", "系统异常。{{[]}}" + ExceptionUtils.getExceptionMessage(ex));
//        }
    }

}
