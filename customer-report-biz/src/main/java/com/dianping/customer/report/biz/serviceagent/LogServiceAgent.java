package com.dianping.customer.report.biz.serviceagent;

public interface LogServiceAgent {
    void info(Class<?> clazz, String msg);

    void info(Object key, String msg);

    void exception(Throwable throwable);

    void exception(String msg, Throwable throwable);

    void exception(Object key, String msg, Throwable throwable);

    void error(Object key, String msg);
}
