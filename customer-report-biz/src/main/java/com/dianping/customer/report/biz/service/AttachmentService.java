package com.dianping.customer.report.biz.service;

import com.dianping.customer.report.biz.dto.Attachment;
import com.dianping.customer.report.biz.dto.UploadAttachmentInfo;

import java.io.File;
import java.io.IOException;

public interface AttachmentService {
    Attachment getFileDownloadUrl(String fileId);

    Attachment uploadFile(UploadAttachmentInfo updateFile, String ownerName);

    byte[] getByteFromFile (File file) throws IOException;
}
