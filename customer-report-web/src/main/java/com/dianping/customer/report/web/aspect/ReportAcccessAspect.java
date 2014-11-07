package com.dianping.customer.report.web.aspect;

import java.lang.reflect.Method;
import java.sql.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.dianping.customer.report.biz.annotation.AccessLogger;
import com.dianping.customer.report.biz.dao.ReportAccessLogDao;
import com.dianping.customer.report.biz.entity.ReportAccessLog;
import com.dianping.customer.report.biz.utils.LoginUtils;
import com.dianping.customer.report.web.action.base.AjaxBase;

@Component
@Aspect
public class ReportAcccessAspect {

	@Autowired
	private ReportAccessLogDao reportAccessLogDao;
	
    @Around("execution( public String com.dianping.customer.report.web.action.*.*.*()) && @annotation(com.dianping.customer.report.biz.annotation.AccessLogger)")
    public Object accessLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            joinPoint.proceed();
            MethodSignature ms = (MethodSignature) joinPoint.getSignature();
            Method m = ms.getMethod();
            AccessLogger accessLogger = AnnotationUtils.findAnnotation(m, AccessLogger.class);
            ReportAccessLog reportAccessLog = this.reportAccessLogDao.findByAccessorIdAndDateAndReportName(LoginUtils.getLoginId(), new Date(new java.util.Date().getTime()),accessLogger.reportName());
            if (reportAccessLog == null){
            	reportAccessLog = new ReportAccessLog();
            	reportAccessLog.setAccessDate(new Date(new java.util.Date().getTime()));
            	reportAccessLog.setAccessorId(LoginUtils.getLoginId());
            	reportAccessLog.setAccessorName(LoginUtils.getLoginName());
            	reportAccessLog.setReportName(accessLogger.reportName());
            	reportAccessLog.setCreateBy(LoginUtils.getLoginId());
            	reportAccessLog.setUpdateBy(LoginUtils.getLoginId());
            	reportAccessLog.setCreateTime(new java.util.Date());
            	reportAccessLog.setUpdateTime(new java.util.Date());
            	reportAccessLogDao.insert(reportAccessLog);
            }
        } catch (Throwable ex) {
            System.out.println("exception: " + ex.getMessage());
        }
        return AjaxBase.SUCCESS;
    }
}
