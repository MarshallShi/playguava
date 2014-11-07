package com.dianping.customer.report.biz.utils;

import com.dianping.customer.report.biz.entity.ServiceResult;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jrose on 10/20/14.
 */
public class SalesForceUtils {

    private static final Logger logger = LoggerFactory.getLogger(SalesForceUtils.class);

    public static ServiceResult getServiceResult(String url, String token) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        ServiceResult serviceResult = null;
        try {
            // specify the get request
            HttpGet getRequest = new HttpGet(url);
            getRequest.addHeader("Authorization", "Bearer " + token);

            HttpResponse httpResponse = httpClient.execute(getRequest);
            HttpEntity entity = httpResponse.getEntity();

            if (entity != null) {
                String string = EntityUtils.toString(entity);
                serviceResult = JsonUtils.fromStr(string, ServiceResult.class);
            }
        } catch (Exception e) {
            logger.warn("getServiceResult error url:{}", url, e);
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpClient.getConnectionManager().shutdown();
        }
        return serviceResult;
    }
}
