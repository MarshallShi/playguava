package com.dianping.customer.report.biz.service.impl;

import com.dianping.customer.report.biz.entity.PageFrameSet;
import com.dianping.customer.report.biz.enums.PageFrameSetPermissionConfigTypeEnum;
import com.dianping.customer.report.biz.service.PageFrameSetService;
import com.dianping.customer.report.biz.service.PermissionService;
import com.dianping.customer.report.biz.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gelin on 14-10-17.
 */
@Service
public class PageFrameSetServiceImpl implements PageFrameSetService {
    @Autowired
    private PermissionService permissionService;

    @Override
    public List<PageFrameSet> getPageFrameSetList(int loginId) {
        List<PageFrameSet> result = getDefaultPageFrameSetList();

        if(ConfigUtils.getSuperAdminList().contains(loginId)) {
            return result;
        }

        Map<String, List<Map>> permissionConfigMap = ConfigUtils.getReportPageFrameSetPermissionConfigMap();
        handlePageFrameSet(result, permissionConfigMap, loginId);
        return result;
    }

    private void handlePageFrameSet(List<PageFrameSet> pageFrameSetList, Map<String, List<Map>> permissionConfigMap, int loginId) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("level", permissionService.isSalesManager(loginId) ? 1 : 0);

        for (String pageFrameSetPath : permissionConfigMap.keySet()) {
            List<Map> permissionList = permissionConfigMap.get(pageFrameSetPath);
            if(CollectionUtils.isNotEmpty(permissionList)) {
                for(Map<String, Object> permissionMap : permissionList) {
                    PageFrameSetPermissionConfigTypeEnum permissionConfigTypeEnum = PageFrameSetPermissionConfigTypeEnum.getByCode((Integer) permissionMap.get("key"));
                    permissionConfigTypeEnum.handlePageFrameSetPermission(pageFrameSetList, pageFrameSetPath, data, permissionMap.get("value"));
                }
            }
        }
    }

    private List<PageFrameSet> getDefaultPageFrameSetList() {
        List<PageFrameSet> result = new ArrayList<PageFrameSet>();
        String frameSetJson = ConfigUtils.getDefaultReportPageFrameSet();
        if (StringUtils.isNotBlank(frameSetJson)) {
            result = JsonUtils.fromArray(frameSetJson, PageFrameSet.class);
        }
        return result;
    }

    public static void main(String... args) {
        List<PageFrameSet> defaultPageFrameSetList = new ArrayList<PageFrameSet>();
        String frameSetJson = "[{children:[{children:[],url:\"customersummary\",desc:\"本地汇总\"}],url:\"customer\",desc:\"重点客户\"},{children:[],url:\"info\",desc:\"竞争情报\"}]";
        if (StringUtils.isNotBlank(frameSetJson)) {
            defaultPageFrameSetList = JsonUtils.fromArray(frameSetJson, PageFrameSet.class);
        }
        PageFrameSetUtils.removeByPath(defaultPageFrameSetList, "customer/customersummary");
        System.out.println("end");
    }
}
