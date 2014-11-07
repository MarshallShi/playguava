package com.dianping.customer.report.biz.dto;

public class UploadAttachmentInfo {
    private String base64;

    public UploadAttachmentInfo(String base64) {
        this.base64 = base64;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
