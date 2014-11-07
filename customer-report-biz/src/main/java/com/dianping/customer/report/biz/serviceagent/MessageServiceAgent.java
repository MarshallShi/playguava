package com.dianping.customer.report.biz.serviceagent;

public interface MessageServiceAgent {
    boolean sendEmail(String mailToAddresses, String mailTitle, String mailBody);
    boolean sendSMS(String mobileNos, String message);
}
