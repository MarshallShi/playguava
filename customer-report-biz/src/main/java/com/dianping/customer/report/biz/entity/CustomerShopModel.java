package com.dianping.customer.report.biz.entity;

/**
 * Created by wangxujin on 14-7-18.
 */
public class CustomerShopModel {

    private int customerId;
    private int shopId;
    private int customerShopType;
    private String customerName;
    private String customerUrlId;


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getCustomerShopType() {
        return customerShopType;
    }

    public void setCustomerShopType(int customerShopType) {
        this.customerShopType = customerShopType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

	public String getCustomerUrlId() {
		return customerUrlId == null ? "" : customerUrlId;
	}

	public void setCustomerUrlId(String customerUrlId) {
		this.customerUrlId = customerUrlId;
	}
}
