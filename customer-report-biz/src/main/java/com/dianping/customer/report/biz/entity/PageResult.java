package com.dianping.customer.report.biz.entity;

import java.util.List;

public class PageResult {
    private int total;
    private List items;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }
}
