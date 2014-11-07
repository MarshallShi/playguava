package com.dianping.customer.report.biz.serviceagent;

import com.dianping.customer.report.biz.entity.CustomerShopModel;
import com.dianping.customer.report.biz.entity.ServiceResult;

import java.util.List;

/**
 * Created by jrose on 10/13/14.
 */
public interface ShopServiceAgent {
    public List<CustomerShopModel> getCustomersByShopId(int shopId,int operatorID);

    public ServiceResult importShop(int shopId,int operatorId);

    public ServiceResult releaseShop(Integer shopId, Integer operatorId);
}
