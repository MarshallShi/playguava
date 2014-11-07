package com.dianping.customer.report.biz.entity;

import java.sql.Date;

public class ReportAccessLog extends BaseEntity{

	private Integer accessorId;
	private String accessorName;
	private String reportName;
	private Date accessDate;
	
	public Integer getAccessorId() {
		return accessorId;
	}
	public void setAccessorId(Integer accessorId) {
		this.accessorId = accessorId;
	}
	public String getAccessorName() {
		return accessorName;
	}
	public void setAccessorName(String accessorName) {
		this.accessorName = accessorName;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public Date getAccessDate() {
		return accessDate;
	}
	public void setAccessDate(Date accessDate) {
		this.accessDate = accessDate;
	}
}
