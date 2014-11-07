package com.dianping.customer.report.biz.serviceagent.impl;

import com.dianping.customer.report.biz.serviceagent.LogServiceAgent;
import com.dianping.customer.report.biz.serviceagent.MessageServiceAgent;
import com.dianping.customer.report.biz.utils.ConfigUtils;
import com.dianping.customer.report.biz.utils.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class LogServiceAgentImpl implements LogServiceAgent {
    private static final Logger logger = Logger.getRootLogger();
    @Autowired
    private MessageServiceAgent messageServiceAgent;

    @Override
    public void info(Class<?> clazz, String msg) {
        info(clazz.getName(), msg);
    }

    @Override
    public void info(Object key, String msg) {
        save(key, msg);
    }

    @Override
    public void exception(Throwable throwable) {
        exception("", throwable);
    }

    @Override
    public void exception(String msg, Throwable throwable) {
        if(null != throwable) {
            StackTraceElement[] stackTraceElements = throwable.getStackTrace();
            if(null != stackTraceElements && stackTraceElements.length > 0) {
                String key = String.format("%s.%s", stackTraceElements[0].getClassName(), stackTraceElements[0].getMethodName());
                error(key, msg + ExceptionUtils.getExceptionMessage(throwable));
            }
            logger.warn("",throwable);
        }
    }

    @Override
    public void exception(Object key, String msg, Throwable throwable) {
        logger.warn("",throwable);
        error(key, msg + ExceptionUtils.getExceptionMessage(throwable));
    }
    @Override
    public void error(Object key, String msg) {
        if (ConfigUtils.isSendExceptionEmail()) {
            messageServiceAgent.sendEmail(ConfigUtils.getProductCenterEmails(), "【团购5】" + key, msg);
        }
//        save("【团购5】:"+key, msg);
    }

    private void save(Object key, String msg) {
        logger.info(String.format("%s %s", key, msg));
    }
}
