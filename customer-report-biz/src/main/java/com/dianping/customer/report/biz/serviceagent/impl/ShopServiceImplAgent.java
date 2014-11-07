package com.dianping.customer.report.biz.serviceagent.impl;

import com.dianping.customer.report.biz.entity.CustomerShopModel;
import com.dianping.customer.report.biz.entity.ServiceResult;
import com.dianping.customer.report.biz.serviceagent.ShopServiceAgent;
import com.dianping.customer.report.biz.utils.SalesForceUtils;
import com.dianping.customerinfo.api.CustomerInfoService;
import com.dianping.customerinfo.api.SalesPerformanceService;
import com.dianping.customerinfo.dto.CustomerLite;
import com.dianping.customerinfo.dto.CustomerShop;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * Created by jrose on 10/13/14.
 */
public class ShopServiceImplAgent implements ShopServiceAgent {

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private SalesPerformanceService salesPerformanceService;

    private String importRestHostURL;
    private String releaseRestHostURL;

    @Override
    public List<CustomerShopModel> getCustomersByShopId(int shopId,int operatorID) {
        List<CustomerShopModel> list = Lists.newArrayList();
        List<CustomerShop> customerShops = customerInfoService.getCustomerShopsFutureByShopID(shopId,operatorID);
        List<Integer> customerIds = Lists.newArrayList();
        for(CustomerShop customerShop : customerShops){
            CustomerShopModel customerShopModel = new CustomerShopModel();
            customerShopModel.setCustomerId(customerShop.getCustomerID());
            customerShopModel.setCustomerShopType(customerShop.getShopType());
            customerShopModel.setShopId(customerShop.getNewShopID());
            list.add(customerShopModel);
            customerIds.add(customerShop.getCustomerID());
        }
        List<CustomerLite> customerLites = customerInfoService.getCustomerLitesFuture(customerIds, operatorID);
        for(CustomerShopModel customerShopModel : list)
            for(CustomerLite customerLite :customerLites){
                if(customerShopModel.getCustomerId() == customerLite.getCustomerID()){
                    customerShopModel.setCustomerName(customerLite.getCustomerName());
                    customerShopModel.setCustomerUrlId(customerLite.getCustomerUrlID());
                }
            }

        return list;
    }

    @Override
    public ServiceResult importShop(int shopId,int operatorId){
        return getServiceResult(shopId, operatorId, importRestHostURL);
    }

    public ServiceResult releaseShop(Integer shopId, Integer operatorId) {
        return getServiceResult(shopId, operatorId, releaseRestHostURL);
    }

    private ServiceResult getServiceResult(Integer shopId, Integer operatorId, String url) {
        return SalesForceUtils.getServiceResult(getRESTUrl(url, shopId, operatorId), salesPerformanceService.getLoginToken());
    }

    private String getRESTUrl(String hostUrl, Integer shopId, Integer loginId){
        StringBuilder sb = new StringBuilder();
        sb.append(hostUrl).append("?shopId=").append(shopId).append("&loginId=").append(loginId);
        return sb.toString();
    }

    public String getImportRestHostURL() {
        return importRestHostURL;
    }

    public void setImportRestHostURL(String importRestHostURL) {
        this.importRestHostURL = importRestHostURL;
    }

    public String getReleaseRestHostURL() {
        return releaseRestHostURL;
    }

    public void setReleaseRestHostURL(String releaseRestHostURL) {
        this.releaseRestHostURL = releaseRestHostURL;
    }
}
