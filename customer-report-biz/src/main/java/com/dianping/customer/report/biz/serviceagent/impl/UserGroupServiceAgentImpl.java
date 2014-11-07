package com.dianping.customer.report.biz.serviceagent.impl;

import com.dianping.customer.report.biz.enums.ApplicationExceptionEnum;
import com.dianping.customer.report.biz.exceptions.ApplicationException;
import com.dianping.customer.report.biz.serviceagent.UserGroupServiceAgent;
import com.dianping.salesbu.api.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 14-10-14.
 */
@Component
public class UserGroupServiceAgentImpl implements UserGroupServiceAgent {

    @Autowired
    private UserGroupService userGroupService;

    @Override
    public int getBUbyLoginId(int loginId) {
        try{
            return userGroupService.getBUbyLoginId(loginId);
        }catch (Exception e){
            throw new ApplicationException( ApplicationExceptionEnum.OPERATION_FAILED,"获取用户BU失败");
        }
    }
}
