package com.dianping.customer.report.biz.serviceagent.impl;

import com.dianping.ba.arc.communication.api.dto.EmailInfo;
import com.dianping.ba.arc.communication.api.dto.SmsInfo;
import com.dianping.ba.arc.communication.api.service.EmailService;
import com.dianping.ba.arc.communication.api.service.SmsService;
import com.dianping.customer.report.biz.serviceagent.MessageServiceAgent;
import com.dianping.customer.report.biz.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MessageServiceAgentImpl implements MessageServiceAgent {
    private static Logger logger = Logger.getRootLogger();

    @Autowired
    private EmailService emailService;
    @Autowired
    private SmsService smsService;

    private static final int RETRY_TIMES = 3;
    private static final int EMAIL_TYPE_ID = 60;
    private static final int SMS_TYPE_ID = 61;
    private static final String FROM_EMAIL = "info@tmail.dianping.com";

    @Override
    public boolean sendEmail(String mailToAddresses, String mailTitle, String mailBody) {
        if (StringUtils.isBlank(mailToAddresses)) {
            return false;
        }
        try {
//            logger.info("mailToAddresses:" + mailToAddresses + ",mailTitle:" + mailTitle + ",mailBody:" + mailBody);
            EmailInfo emailInfo = new EmailInfo();
            emailInfo.setAddDate(new Date());
            emailInfo.setEmailBody(StringUtils.valueOf(mailBody));
            emailInfo.setEmailTitle(mailTitle);
            emailInfo.setEmailTypeID(EMAIL_TYPE_ID);
            emailInfo.setFromEmail(FROM_EMAIL);
            emailInfo.setFromName("");
            emailInfo.setReEmail("");
            emailInfo.setStatus(0);
            emailInfo.setTryTimes(RETRY_TIMES);
            emailInfo.setUpdateDate(new Date());

            String[] mailToAddressArray = mailToAddresses.split(",");
            for (String mailToAddress : mailToAddressArray) {
                emailInfo.setEmail(mailToAddress);
                emailInfo.setGuidID(java.util.UUID.randomUUID().toString());
                emailService.Save(emailInfo);
            }
            return true;
        } catch (Exception e) {
            logger.error("sendEmail执行异常", e);
        }
        return false;
    }

    @Override
    public boolean sendSMS(String mobileNos, String message) {
        if (StringUtils.isBlank(mobileNos)) {
            return false;
        }
        try {
//            logger.info("mobileNos:" + mobileNos + ",message:" + message);
            SmsInfo smsInfo = new SmsInfo();
            smsInfo.setAddDate(new Date());
            smsInfo.setAllTime(1);
            smsInfo.setMessage(message);
            smsInfo.setRemark("");
            smsInfo.setSmsChannel(0);
            smsInfo.setSmsTypeID(SMS_TYPE_ID);
            smsInfo.setStatus(0);
            smsInfo.setTryTimes(RETRY_TIMES);
            smsInfo.setUpdateTime(new Date());

            String[] mobileNoArray = mobileNos.split(",");
            for (String mobileNo : mobileNoArray) {
                smsInfo.setMobileNo(mobileNo);
                smsInfo.setGuid(java.util.UUID.randomUUID().toString());
                smsService.Save(smsInfo);
            }
            return true;
        } catch (Exception e) {
            logger.error("sendSMS执行异常", e);
        }
        return false;
    }
}
