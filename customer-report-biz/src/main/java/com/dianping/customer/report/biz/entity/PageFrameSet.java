package com.dianping.customer.report.biz.entity;

import java.util.List;

/**
 * Created by gelin on 14-10-17.
 */
public class PageFrameSet {
    private String desc;
    private String url;
    private List<PageFrameSet> children;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<PageFrameSet> getChildren() {
        return children;
    }

    public void setChildren(List<PageFrameSet> children) {
        this.children = children;
    }
}
