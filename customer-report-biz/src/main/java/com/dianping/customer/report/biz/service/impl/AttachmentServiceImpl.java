package com.dianping.customer.report.biz.service.impl;

import com.dianping.customer.report.biz.dto.Attachment;
import com.dianping.customer.report.biz.dto.UploadAttachmentInfo;
import com.dianping.customer.report.biz.service.AttachmentService;
import com.dianping.customer.report.biz.serviceagent.LogServiceAgent;
import com.dianping.customer.report.biz.utils.HintUtils;
import com.dianping.filecloud.download.api.FileDownloadAPI;
import com.dianping.filecloud.storage.api.HttpUploadAPI;
import com.dianping.filecloud.storage.api.Token;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private LogServiceAgent logServiceAgent;

    @Override
    public Attachment getFileDownloadUrl(String fileId) {
        FileDownloadAPI fileDownloadAPI = new FileDownloadAPI(biz, account, downloadKey);
        fileDownloadAPI.setExpiredTime(expiredTime);
        fileDownloadAPI.setRequestUrl(downloadUrl);

        Attachment attachment = new Attachment();
        attachment.setFileId(fileId);
        attachment.setUrl(fileDownloadAPI.formatFileUrl(fileId, fileId));
        attachment.setThumbUrl(fileDownloadAPI.formatFileUrl(fileId + "_1", fileId + "_thumb"));

        return attachment;
    }

    @Override
    public Attachment uploadFile(UploadAttachmentInfo updateFile, String ownerName) {
        HttpUploadAPI uploadApi = new HttpUploadAPI(account, uploadKey);
        uploadApi.setRequestURL(uploadUrl);
        uploadApi.setConnectTimeout(10000);
        uploadApi.setReadTimeout(10000);

        FileDownloadAPI fileDownloadAPI = new FileDownloadAPI(biz, account, downloadKey);
        fileDownloadAPI.setExpiredTime(expiredTime);
        fileDownloadAPI.setRequestUrl(downloadUrl);

        Token token = new Token();
        token.setBiz(biz);
        token.setAccount(account);
        token.setExpiredTime(expiredTime);
        token.setOwnerName(ownerName);

        Map<String, String> header = new HashMap<String, String>();
        Attachment attachment = new Attachment();
        header.put("ClientIPByProxy", "127.0.0.1");
            try {
                Map<String, String> map = uploadApi
                        .execute(token, updateFile.getBase64(), header);
                String fileId = map.get("key");
                String code = map.get("code");
                if ("200".equals(code)) {
                    attachment.setFileId(fileId);
                    attachment.setUrl(fileDownloadAPI.formatFileUrl(fileId, fileId));
                    attachment.setThumbUrl(fileDownloadAPI.formatFileUrl(fileId + "_1", fileId + "_thumb"));
                } else {
                    logServiceAgent.error("upload failed", "upload file error " + map);
                    return null;
                }
            } catch (Exception e) {
                logServiceAgent.exception("upload failed", "upload file error", e);
                return null;
            }
        return attachment;
    }

    /**
     * 将file转化为byte数组
     * @param file
     * @return
     */
    @Override
    public byte[] getByteFromFile (File file) throws IOException {
        InputStream inFile = new FileInputStream(file);
        HintUtils.assertRes(file.length()<=Integer.MAX_VALUE,"文件长度过长");

        byte[] bytes = new byte[(int)(file.length())];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead=inFile.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
        HintUtils.assertRes(offset >= bytes.length,"文件转化为二进制数组时出错");
        inFile.close();
        return bytes;
    }


    private String biz;
    private String account;
    private long expiredTime;
    private String uploadUrl;
    private String uploadKey;
    private String downloadKey;
    private String downloadUrl;

    public String getBiz() {
        return biz;
    }

    public void setBiz(String biz) {
        this.biz = biz;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(long expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public String getUploadKey() {
        return uploadKey;
    }

    public void setUploadKey(String uploadKey) {
        this.uploadKey = uploadKey;
    }

    public String getDownloadKey() {
        return downloadKey;
    }

    public void setDownloadKey(String downloadKey) {
        this.downloadKey = downloadKey;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
