package com.dianping.customer.report.biz.utils;

import com.dianping.customer.report.biz.entity.PageFrameSet;

import java.util.List;

/**
 * Created by gelin on 14-10-17.
 */
public class PageFrameSetUtils {
    public static PageFrameSet getByPath(List<PageFrameSet> pageFrameSetList, String path) {
        if (CollectionUtils.isNotEmpty(pageFrameSetList) && StringUtils.isNotBlank(path)) {
            String pageFrameSetUrl = path.split("/")[0];
            for (PageFrameSet pageFrameSet : pageFrameSetList) {
                if (pageFrameSet.getUrl().toLowerCase().equals(pageFrameSetUrl.toLowerCase())) {
                    int index = path.indexOf("/");
                    if (index < 0) {
                        return pageFrameSet;
                    } else {
                        return getByPath(pageFrameSet.getChildren(), path.substring(index + 1, path.length()));
                    }
                }
            }
        }
        return null;
    }

    public static void removeByPath(List<PageFrameSet> pageFrameSetList, String path) {
        if (CollectionUtils.isNotEmpty(pageFrameSetList) && StringUtils.isNotBlank(path)) {
            int index = path.indexOf("/");
            if (index < 0) {
                PageFrameSet pageFrameSet = getByPath(pageFrameSetList, path);
                if (pageFrameSet != null) {
                    pageFrameSetList.remove(pageFrameSet);
                }
            } else {
                String currentUrl = path.substring(0, index);
                for (PageFrameSet pageFrameSet : pageFrameSetList) {
                    if (pageFrameSet.getUrl().toLowerCase().equals(currentUrl.toLowerCase())) {
                        removeByPath(pageFrameSet.getChildren(), path.substring(index + 1, path.length()));
                    }
                }
            }
        }
    }
}
